package com.fiap.areslife.controller;

import com.fiap.areslife.dto.request.LoginRequest;
import com.fiap.areslife.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager manager;
    private final JwtService jwtService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        // O manager já busca o usuário, compara o hash BCrypt e lança BadCredentialsException se errar
        manager.authenticate(
            new UsernamePasswordAuthenticationToken(request.email(), request.senha())
        );

        return jwtService.gerarToken(request.email());
    }
}
