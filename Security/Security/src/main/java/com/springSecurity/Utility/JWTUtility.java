package com.springSecurity.Utility;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtility {
	private final String keyString="manish-wadkar-1234567890-1234567890-1234567890#";
	public final SecretKey key=Keys.hmacShaKeyFor(keyString.getBytes());
	
	public String genrateToken(String username) {
		return Jwts.builder().
				setHeaderParam("masnih", "maaaa")
		.setSubject(username)
		.setIssuedAt(new Date())
		.setExpiration(new Date(System.currentTimeMillis()+(2000*60*6)))
		.signWith(key,SignatureAlgorithm.HS256)
		.compact();
	}

	public Claims extract(String jWTtoken) {
		return Jwts.parserBuilder()           // Use parserBuilder() instead of parser()
		        .setSigningKey(key)                    // Use setSigningKey() instead of verifyWith()
		        .build()
		        .parseClaimsJws(jWTtoken)                 // Use parseClaimsJws() instead of parseSignedClaims()
		        .getBody();
		
	}
	
	public String extractUserName(String jWTtoken) {
		return extract(jWTtoken).getSubject();
	}

	public boolean validateToken(String username, String jwtToken) {
		return(username.equals(extractUserName(jwtToken)) && !isexpired(jwtToken));
		
	}

	private boolean isexpired(String jwtToken) {
		return extract(jwtToken).getExpiration().before(new Date());
			
	}
	


}
