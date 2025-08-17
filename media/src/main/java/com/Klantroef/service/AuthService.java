package com.Klantroef.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Klantroef.dto.AuthRequest;
import com.Klantroef.dto.JwtResponse;
import com.Klantroef.dto.SignUpRequest;
import com.Klantroef.model.AdminUser;
import com.Klantroef.repository.AdminUserRepository;
import com.Klantroef.security.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private AdminUserRepository adminUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    public AdminUser signup(SignUpRequest signUpRequest) {
        if (adminUserRepository.findByEmail(signUpRequest.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }
        String hashedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        AdminUser newUser = new AdminUser(signUpRequest.getEmail() , hashedPassword);
        return adminUserRepository.save(newUser);
    }

    public JwtResponse login(AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());
        final String token = jwtUtil.generateToken(userDetails);
        return new JwtResponse(token);
    }
}