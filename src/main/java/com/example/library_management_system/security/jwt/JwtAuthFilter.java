package com.example.library_management_system.security.jwt;

import com.example.library_management_system.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private JwtService jwtService;
    private UserDetailsService userDetailsService;
    @Autowired
    public JwtAuthFilter(JwtService jwtService,UserDetailsService userDetailsService){
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if(header == null || !header.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        String token = header.substring(7);
        String email = jwtService.extractSubjet(token);
        UserDetails user = this.userDetailsService.loadUserByUsername(email);
        if(user!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    user.getAuthorities()
            );
            auth.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request,response);
    }
}
