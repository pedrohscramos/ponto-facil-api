package br.com.pontofacil.pontofacilapi.service;

import br.com.pontofacil.pontofacilapi.dto.AtualizarUsuarioRequest;
import br.com.pontofacil.pontofacilapi.dto.CriarUsuarioRequest;
import br.com.pontofacil.pontofacilapi.dto.UserResponse;
import br.com.pontofacil.pontofacilapi.entity.User;
import br.com.pontofacil.pontofacilapi.exception.AcessoNegadoException;
import br.com.pontofacil.pontofacilapi.exception.RecursoNaoEncontradoException;
import br.com.pontofacil.pontofacilapi.exception.RegraNegocioException;
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
    private final PlanoService planoService;


    public void criarUsuario(String emailAdmin, CriarUsuarioRequest request){



        User admin = userRepository.findByEmail(emailAdmin)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Administrador não encontrado."));

        planoService.validarCriacaoUsuario(admin.getEmpresa());

        if(userRepository.existsByEmail(request.email())){
            throw new RegraNegocioException("E-mail já cadastrado");
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
                .orElseThrow(() -> new RecursoNaoEncontradoException("Administrador não encontrado."));

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
                .orElseThrow(() -> new RecursoNaoEncontradoException("Administrador não encontrado."));

        User usuario = userRepository.findById(userId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));

        if(!usuario.getEmpresa().getId().equals(admin.getEmpresa().getId())){
            throw new AcessoNegadoException("Acesso negado");
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
                .orElseThrow(() -> new RecursoNaoEncontradoException("Administrador não encontrado."));

        User usuario = userRepository.findById(userId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));

        if(!usuario.getEmpresa().getId().equals(admin.getEmpresa().getId())){
            throw new AcessoNegadoException("Acesso negado");
        }

        userRepository.delete(usuario);
    }
}
