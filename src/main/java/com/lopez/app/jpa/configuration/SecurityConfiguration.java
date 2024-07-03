package com.lopez.app.jpa.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;
import static org.springframework.security.config.Customizer.withDefaults;




 @Configuration
public class SecurityConfiguration {

   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
       .authorizeHttpRequests(authorizeRequests ->
           authorizeRequests
               .requestMatchers(HttpMethod.POST, "/api/clientes/**").authenticated()
               .requestMatchers(HttpMethod.GET,"/api/clientes/**").hasRole("ADMIN")
               .anyRequest().permitAll()
       )
       .csrf(csrf -> csrf.disable())
       .httpBasic(withDefaults());
		return http.build();
	}



}
