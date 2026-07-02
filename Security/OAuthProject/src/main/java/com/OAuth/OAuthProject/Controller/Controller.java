package com.OAuth.OAuthProject.Controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	
	@GetMapping("/public")
	public String publicMethod() {
		return "Demo";
	}
	
	@GetMapping("/private")
	public String privateMethod(OAuth2AuthenticationToken token) {
		return "Private"+token.getPrincipal().getAttribute("email");
	}
}
