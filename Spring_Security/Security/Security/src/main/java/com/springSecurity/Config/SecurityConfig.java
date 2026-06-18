	package com.springSecurity.Config;
	
	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Configuration;
	import org.springframework.security.authentication.AuthenticationManager;
	import org.springframework.security.authentication.AuthenticationProvider;
	import org.springframework.security.authentication.ProviderManager;
	import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
	import org.springframework.security.config.annotation.web.builders.HttpSecurity;
	import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
	import org.springframework.security.core.userdetails.UserDetailsService;
	import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
	import org.springframework.security.crypto.password.PasswordEncoder;
	import org.springframework.security.web.SecurityFilterChain;
	
	import com.springSecurity.Service.SecurityUserDetails;
	
	import static org.springframework.security.config.Customizer.withDefaults;
	
	import org.springframework.beans.factory.annotation.Autowired;
	
	
	
	@Configuration
	@EnableWebSecurity
	public class SecurityConfig {
		
		@Autowired
		private UserDetailsService UserDetailsService;
		
		@Bean
		public SecurityFilterChain filterChain(HttpSecurity http) {
			http
			
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth->{
				auth.requestMatchers("/authenticate").permitAll()
				.anyRequest().authenticated();
				
			})
			.httpBasic(withDefaults());
			return http.build();
		}
		
		
	//		@Bean
	//		public UserDetailsService securityDetails() {
	//			return new SecurityUserDetails();
	//		}
		
		@Bean
		public PasswordEncoder passwordEncoder() {
			return  new BCryptPasswordEncoder();
		}
		
		
	
		
		@Bean
		public AuthenticationManager authenticationManger(SecurityUserDetails securityUserDetails,
															PasswordEncoder passwordEncoder) {
			
			 DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(securityUserDetails);
			 daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
			return  new ProviderManager(daoAuthenticationProvider);
		}
	
	}
