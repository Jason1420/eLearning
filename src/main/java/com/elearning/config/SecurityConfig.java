package com.elearning.config;

import com.elearning.jwt.JwtAuthEntryPoint;
import com.elearning.jwt.JwtAuthHandler;
import com.elearning.jwt.JwtAuthenticationFilter;
import com.elearning.service.security.CustomUserDetailServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    private final CustomUserDetailServiceImpl userDetailServiceImpl;
    private final JwtAuthEntryPoint jwtAuthEntryPoint;
    private final JwtAuthHandler jwtAuthHandler;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                                .requestMatchers("/signup").permitAll()
                                .requestMatchers("/signup/**").permitAll()
                                .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
                                .requestMatchers("/verify").permitAll()
                                .requestMatchers("/profile").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/student").hasAnyAuthority("ADMIN", "MANAGER")
                                .requestMatchers(HttpMethod.GET, "/api/student/**").hasAnyAuthority("ADMIN", "MANAGER")
                                .requestMatchers(HttpMethod.GET, "/api/student/search/**").hasAnyAuthority("ADMIN", "MANAGER")
                                .requestMatchers(HttpMethod.POST, "/api/student").hasAnyAuthority("ADMIN", "MANAGER", "USER")
                                .requestMatchers(HttpMethod.POST, "/api/student/**").hasAnyAuthority("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/student/**").hasAnyAuthority("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/student/**").hasAnyAuthority("ADMIN", "MANAGER")
                                .requestMatchers(HttpMethod.DELETE, "/api/student").hasAnyAuthority("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/user/**").hasAnyAuthority("MANAGER", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/login").permitAll()
                                .anyRequest().authenticated()
                )
                .logout(LogoutConfigurer::permitAll)
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .userDetailsService(userDetailServiceImpl)
                .exceptionHandling(exceptionHanding -> exceptionHanding
                        .accessDeniedHandler(this.jwtAuthHandler)
                        .authenticationEntryPoint(this.jwtAuthEntryPoint));
        http.addFilterBefore(jwtAuthenticationFilters(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilters() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
