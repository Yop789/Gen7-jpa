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

import com.lopez.app.jpa.filter.JwtRequestFilter;

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
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/api/usuarios/**").permitAll()
                        .requestMatchers(HttpMethod.POST).hasRole("ADMIN")
                        .anyRequest().authenticated()
                ).sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
