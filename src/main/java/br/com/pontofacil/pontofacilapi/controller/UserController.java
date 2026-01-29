package br.com.pontofacil.pontofacilapi.controller;

import br.com.pontofacil.pontofacilapi.dto.UserResponse;
import br.com.pontofacil.pontofacilapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public UserResponse me(Authentication authentication) {
        String email = authentication.getName();
        return userService.getMe(email);
    }
}
