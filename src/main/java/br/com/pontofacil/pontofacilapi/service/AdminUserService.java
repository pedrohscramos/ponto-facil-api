package br.com.pontofacil.pontofacilapi.service;

import br.com.pontofacil.pontofacilapi.dto.AtualizarUsuarioRequest;
import br.com.pontofacil.pontofacilapi.dto.CriarUsuarioRequest;
import br.com.pontofacil.pontofacilapi.dto.UserResponse;
import br.com.pontofacil.pontofacilapi.entity.User;
import br.com.pontofacil.pontofacilapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void criarUsuario(String emailAdmin, CriarUsuarioRequest request){

        User admin = userRepository.findByEmail(emailAdmin)
                .orElseThrow(() -> new IllegalStateException("Administrador não encontrado."));

        if(userRepository.existsByEmail(request.email())){
            throw new RuntimeException("E-mail já cadastrado");
        }

        User novoUsuario = new User();
        novoUsuario.setEmail(request.email());
        novoUsuario.setPassword(passwordEncoder.encode(request.password()));
        novoUsuario.setRole(request.role());
        novoUsuario.setEmpresa(admin.getEmpresa());

        userRepository.save(novoUsuario);
    }

    public List<UserResponse> listarUsuarios(String emailAdmin){

        User admin = userRepository.findByEmail(emailAdmin)
                .orElseThrow(() -> new IllegalStateException("Administrador não encontrado."));

        return userRepository.findByEmpresa(admin.getEmpresa())
                .stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getEmail(),
                        user.getRole()
                ))
                .toList();
    }

    public void atualizarUsuario(String emailAdmin, Long userId, AtualizarUsuarioRequest request){

        User admin = userRepository.findByEmail(emailAdmin)
                .orElseThrow(() -> new IllegalStateException("Administrador não encontrado."));

        User usuario = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if(!usuario.getEmpresa().getId().equals(admin.getEmpresa().getId())){
            throw new IllegalStateException("Acesso negado");
        }

        if(request.email() != null){
            usuario.setEmail(request.email());
        }
        if(request.role() != null) {
            usuario.setRole(request.role());
        }

        userRepository.save(usuario);
    }

    public void deletarUsuario(String emailAdmin, Long userId){

        User admin = userRepository.findByEmail(emailAdmin)
                .orElseThrow(() -> new IllegalStateException("Administrador não encontrado."));

        User usuario = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if(!usuario.getEmpresa().getId().equals(admin.getEmpresa().getId())){
            throw new IllegalStateException("Acesso negado");
        }

        userRepository.delete(usuario);
    }
}
