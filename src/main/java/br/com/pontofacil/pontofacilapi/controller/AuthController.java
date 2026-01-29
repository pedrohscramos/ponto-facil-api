package br.com.pontofacil.pontofacilapi.controller;

import br.com.pontofacil.pontofacilapi.dto.LoginRequest;
import br.com.pontofacil.pontofacilapi.dto.TokenResponse;
import br.com.pontofacil.pontofacilapi.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest request){

        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(), request.password()
                )
        );

        String token = jwtService.generateToken(auth.getName());
        return new TokenResponse(token);
    }
}
