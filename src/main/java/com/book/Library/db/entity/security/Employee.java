package com.book.Library.db.entity.security;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
public class Employee implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();  // Здесь можно добавить роли или права доступа для пользователя
    }

    @Override
    public String getUsername() {
        return login;  // Возвращаем login как username
    }

    @Override
    public String getPassword() {
        return password;  // Возвращаем пароль пользователя
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // Учетная запись не просрочена
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // Учетная запись не заблокирована
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // Пароль не истек
    }

    @Override
    public boolean isEnabled() {
        return true;  // Учетная запись активна
    }
}