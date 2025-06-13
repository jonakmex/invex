package com.invex.port.api.handler;

import com.invex.port.api.security.JwtUtil;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public class LoginHandler {
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public LoginHandler(JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public Mono<ServerResponse> login(ServerRequest request) {
        return request.bodyToMono(LoginRequest.class)
                .flatMap(login -> {
                    // Replace with your user lookup and password check
                    if ("user".equals(login.getUsername()) && "password".equals(login.getPassword())) {
                        String token = jwtUtil.generateToken(login.getUsername());
                        return ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(new JwtResponse(token));
                    } else {
                        return ServerResponse.status(401).build();
                    }
                });
    }

    public static class LoginRequest {
        private String username;
        private String password;
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class JwtResponse {
        private String token;
        public JwtResponse(String token) { this.token = token; }
        public String getToken() { return token; }
        public void setToken(String token) { this.token = token; }
    }
}
