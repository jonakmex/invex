package com.invex.port.api.security;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import reactor.core.publisher.Mono;


public class JwtReactiveAuthenticationManager implements ReactiveAuthenticationManager {
    private final JwtUtil jwtUtil;

    public JwtReactiveAuthenticationManager(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();
        if (jwtUtil.validateToken(token)) {
            String username = jwtUtil.extractUsername(token);
            return Mono.just(new UsernamePasswordAuthenticationToken(username, token, authentication.getAuthorities()));
        }
        return Mono.empty();
    }
}
