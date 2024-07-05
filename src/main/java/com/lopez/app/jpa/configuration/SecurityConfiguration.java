package com.lopez.app.jpa.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.lopez.app.jpa.filter.JwtRequestFilter;

import java.time.Duration;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    /*@Bean
    public UsuariosService us() {
        return new UsuariosService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                if ("user".equals(username)) {
                    return User.withUsername("user")

                            .password(passwordEncoder().encode("password"))
                            .roles("USER").build();
                } else {
                    throw new UsernameNotFoundException("User not found");
                }
            }
        };
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder,UserDetailsService userDetailsService)
            throws Exception {
                AuthenticationManagerBuilder authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
		
                authBuilder.userDetailsService(userDetailsService)
               .passwordEncoder(passwordEncoder);
                
                 // Retornar el AuthenticationManager configurado
               return authBuilder.build();
        
    }

    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // http
    // .authorizeHttpRequests(authorizeRequests ->
    // authorizeRequests
    // .requestMatchers(HttpMethod.POST, "/api/clientes/**").authenticated()
    // .requestMatchers(HttpMethod.GET,"/api/clientes/**").hasRole("ADMIN")
    // .anyRequest().permitAll()
    // )
    // .csrf(csrf -> csrf.disable())
    // .httpBasic(withDefaults());
    // return http.build();
    // }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
        .cors(cors-> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/api/usuarios/**").permitAll()
                        .requestMatchers(HttpMethod.POST).hasRole("ADMIN")
                        .anyRequest().authenticated()
                ).sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


       @Bean
   CorsConfigurationSource corsConfigurationSource() {
       CorsConfiguration cc = new CorsConfiguration();
     
       cc.setAllowedHeaders(Arrays.asList("Origin,Accept", "X-Requested-With", "Content-Type", "Access-Control-Request-Method", "Access-Control-Request-Headers", "Authorization"));
       cc.setExposedHeaders(Arrays.asList("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
     
       cc.setAllowedOrigins(Arrays.asList("/*"));
      
       cc.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "PUT", "PATCH"));
      
       cc.addAllowedOriginPattern("*");      
      
       cc.setMaxAge(Duration.ZERO);
       cc.setAllowCredentials(Boolean.TRUE);
       UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
       source.registerCorsConfiguration("/**", cc);
       return source;
   }
}
