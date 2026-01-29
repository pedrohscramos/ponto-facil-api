package br.com.pontofacil.pontofacilapi.service;

import br.com.pontofacil.pontofacilapi.dto.UserResponse;
import br.com.pontofacil.pontofacilapi.entity.User;
import br.com.pontofacil.pontofacilapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse getMe(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );
    }
}
