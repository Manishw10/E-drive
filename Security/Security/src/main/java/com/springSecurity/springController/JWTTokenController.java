package com.springSecurity.springController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springSecurity.Entity.Authrequest;
import com.springSecurity.Utility.JWTUtility;

@RestController
public class JWTTokenController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	
	@Autowired
	JWTUtility jwtUtility;
	
	@GetMapping("/authenticate")
	public ResponseEntity<String>  getToken(@RequestBody Authrequest authreq) {
	
		try
		{
			authenticationManager.authenticate(		
				new UsernamePasswordAuthenticationToken(authreq.getUsername(), authreq.getPassword())
				);
			return ResponseEntity.ok(jwtUtility.genrateToken(authreq.getUsername()));
			
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
			.body("Bad Creads");
			
		}
		
		
		
	}
}
