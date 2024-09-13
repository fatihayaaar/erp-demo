package com.fayardev.erpdemo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fayardev.erpdemo.dto.AuthDTO;
import com.fayardev.erpdemo.service.CustomUserDetailsService;
import com.hazelcast.shaded.org.json.JSONObject;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static com.fayardev.erpdemo.security.constant.AuthConstant.TOKEN_PREFIX;

@RequiredArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {
            AuthDTO user = new ObjectMapper().readValue(req.getInputStream(), AuthDTO.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {
        UserDetails userDetails = userService.loadUserByUsername(((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername());
        String token = jwtUtil.generateToken(userDetails);

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("token", TOKEN_PREFIX + token);

        PrintWriter writer = res.getWriter();
        writer.write(jsonResponse.toString());
        writer.flush();
    }
}