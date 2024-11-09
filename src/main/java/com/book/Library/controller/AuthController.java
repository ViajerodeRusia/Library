package com.book.Library.controller;

import com.book.Library.controller.dto.security.AuthToken;
import com.book.Library.service.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/login")
    public AuthToken login (@RequestParam String login, @RequestParam String password) {
        return authService.login(login, password);
    }
    @PostMapping("/refresh")
    public String refresh(@RequestParam String refreshToken) {
        return authService.refreshToken(refreshToken);
    }
    @PostMapping("/register")
    public void register(@RequestParam String login, @RequestParam String password) {
        authService.registerEmployee(login, password);
    }
}
