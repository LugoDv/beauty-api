package com.lugo.beauty_api.auth;

import com.lugo.beauty_api.mapper.UserMapper;
import com.lugo.beauty_api.model.User;
import com.lugo.beauty_api.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

private final AuthenticationService authenticationService;

@PostMapping("/register")
public ResponseEntity<AuthResponse> register (@RequestBody RegisterRequest request){

    User user = UserMapper.toEntity(request);

    return  ResponseEntity.ok(authenticationService.register(user));
}

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {

        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
