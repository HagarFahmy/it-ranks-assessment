package com.it_ranks.service;

import com.it_ranks.dto.AuthenticationResponse;
import com.it_ranks.dto.RegisterRequest;
import com.it_ranks.entity.User;
import com.it_ranks.enums.Role;
import com.it_ranks.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register (RegisterRequest request) {
        User user = new User ( );
        user.setPassword (passwordEncoder.encode (request.getPassword ( ) ));
        user.setUserName (request.getUserName ( ));
        user.setRole (Role.USER);
        userService.save (user);
        String token = jwtService.generateToken (user);
        return new AuthenticationResponse (token);
    }

    public AuthenticationResponse login (RegisterRequest request) {
        authenticationManager.authenticate (
                new UsernamePasswordAuthenticationToken (request.getUserName (),request.getPassword ()));
        User user = (User) userService.loadUserByUsername (request.getUserName ());
        String token = jwtService.generateToken (user);
        return new AuthenticationResponse (token);
    }
}
