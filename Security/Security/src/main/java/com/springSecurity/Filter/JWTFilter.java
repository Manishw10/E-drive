package com.springSecurity.Filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import com.springSecurity.Service.SecurityUserDetails;
import com.springSecurity.Utility.JWTUtility;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JWTFilter extends OncePerRequestFilter{
	
	@Autowired
	public JWTUtility jwtUtility;
	
	@Autowired
	public SecurityUserDetails securityUserDetails;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authorizationHeader=request.getHeader("Authorization");
		String username=null;
		
		if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer")){
			String JWTtoken=authorizationHeader.substring(7);
			username=jwtUtility.extractUserName(JWTtoken);
		
		
		if(username!=null) {
			UserDetails user=securityUserDetails.loadUserByUsername(username);
			if(jwtUtility.validateToken(user.getUsername(), JWTtoken)) {
				UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(user, null,user.getAuthorities());
				auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(auth);
				
			}
			
		}
		}
		
		filterChain.doFilter(request, response);
		
		
	}
	
	

}
