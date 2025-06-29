package com.lugo.beauty_api.service;

import com.lugo.beauty_api.auth.AuthRequest;
import com.lugo.beauty_api.auth.AuthResponse;
import com.lugo.beauty_api.auth.RegisterRequest;
import com.lugo.beauty_api.exception.NotFoundException;
import com.lugo.beauty_api.mapper.UserMapper;
import com.lugo.beauty_api.model.Rol;
import com.lugo.beauty_api.model.User;
import com.lugo.beauty_api.repository.RolRepositoy;
import com.lugo.beauty_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RolRepositoy rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) throw new NotFoundException("error");

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Rol rol = rolRepository.findByName("CLIENT")
                .orElseThrow(() -> new NotFoundException("Rol no encontrado"));
        user.setRole(rol);


        User saved = userRepository.save(user);


        String jwtToken = jwtService.generateToken(saved);

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponse authenticate(AuthRequest request){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found"));



        String jwtToken = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}
