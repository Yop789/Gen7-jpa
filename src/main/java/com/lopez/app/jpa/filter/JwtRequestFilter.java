package com.lopez.app.jpa.filter;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.lopez.app.jpa.services.UsuariosService;
import com.lopez.app.jpa.utils.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private JwtUtil jwtUtil;

    private String username= null;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if(request.getServletPath().matches("/api/usuarios/login") || request.getServletPath().matches("/api/usuarios/registry")) {
			filterChain.doFilter(request, response);
		}
		else {
			String autorizationHeader = request.getHeader("Authorization");
			String jwt = null;
			if(autorizationHeader != null && autorizationHeader.startsWith("Bearer ")) {
				jwt = autorizationHeader.substring(7);
				username = jwtUtil.extractUsername(jwt);
				//claims = jwtUtil.extractAllClaims(token);
			}
			
			if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = usuariosService.loadUserByUsername(username);
				if(jwtUtil.validateToken(jwt, userDetails.getUsername())) {
					UsernamePasswordAuthenticationToken u = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					u.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(u);
				}
				
			}
			filterChain.doFilter(request, response);
		}
		
	}

	
    

       
    

}
