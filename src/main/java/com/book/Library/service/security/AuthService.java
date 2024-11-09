package com.book.Library.service.security;

import com.book.Library.security.JwtTokenProvider;
import com.book.Library.controller.dto.security.AuthToken;
import com.book.Library.db.entity.security.Employee;
import com.book.Library.db.repository.security.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final EmployeeRepository employeeRepository;
    private final JwtTokenProvider tokenProvider; // Внедряем JwtTokenProvider
    private final BCryptPasswordEncoder passwordEncoder;
    private final Map<String, String> refreshTokens = new HashMap<>();

    public AuthToken login(String login, String password) {
        Employee employee = employeeRepository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("Invalid login or password"));

        if (passwordEncoder.matches(password, employee.getPassword())) {
            String accessToken = tokenProvider.generateAccessToken(login);
            String refreshToken = tokenProvider.generateRefreshToken(login);
            refreshTokens.put(refreshToken, login);
            return new AuthToken(accessToken, refreshToken);
        }
        throw new RuntimeException("Invalid login or password");
    }

    public String refreshToken(String refreshToken) {
        String login = refreshTokens.get(refreshToken);
        if (login != null) {
            return tokenProvider.generateAccessToken(login);
        }
        throw new RuntimeException("Invalid refresh token");
    }

    public void registerEmployee(String login, String password) {
        Employee employee = new Employee();
        employee.setLogin(login);
        employee.setPassword(passwordEncoder.encode(password));
        employeeRepository.save(employee);
    }
}
