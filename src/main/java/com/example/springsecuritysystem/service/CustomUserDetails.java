package com.example.springsecuritysystem.service;

import com.example.springsecuritysystem.entity.system.user.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails extends Users implements UserDetails {

    public CustomUserDetails(Users users) {
        super(users);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        super.getRoles().forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole())));
        super.getRoles().forEach(role -> role.getPermissions().forEach(permission -> grantedAuthorities.add(new SimpleGrantedAuthority(permission.getName()))));
        super.getPermissions().forEach(permission -> grantedAuthorities.add(new SimpleGrantedAuthority(permission.getName())));
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return super.getIsAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return super.getIsAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return super.getIsCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return super.getIsEnabled();
    }
}
