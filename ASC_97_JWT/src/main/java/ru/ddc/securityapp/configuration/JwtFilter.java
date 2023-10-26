package ru.ddc.securityapp.configuration;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.ddc.securityapp.security.JwtUtil;
import ru.ddc.securityapp.service.PersonDetailsService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final PersonDetailsService personDetailsService;

    public JwtFilter(JwtUtil jwtUtil,
                     PersonDetailsService personDetailsService) {
        this.jwtUtil = jwtUtil;
        this.personDetailsService = personDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String header = httpServletRequest.getHeader("Authorization");
        if (header != null && !header.isBlank() && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            if (token.isBlank()) {
                httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT");
            } else {
                try {
                    String username = jwtUtil.validateTokenAndRetrieveClaim(token);
                    UserDetails userDetails = personDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            userDetails.getPassword(),
                            userDetails.getAuthorities()
                    );
                    if (SecurityContextHolder.getContext().getAuthentication() == null) {
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                } catch (JWTVerificationException | UsernameNotFoundException e) {
                    httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT");
                }
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
