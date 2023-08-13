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
                        .requestMatchers("/confirm").permitAll()
                        .requestMatchers("/verify").permitAll()
                        .requestMatchers("/admin").hasAnyAuthority("ADMIN")
                        .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
                        .requestMatchers("/register/**").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/user/**").hasAnyAuthority("ADMIN", "TEACHER", "STUDENT")
                        .requestMatchers(HttpMethod.GET, "/user/**").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/user").hasAnyAuthority("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/login").permitAll()

                        .requestMatchers(HttpMethod.POST, "/api/v1/class").hasAnyAuthority("ADMIN", "TEACHER")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/class/**").hasAnyAuthority("ADMIN", "TEACHER")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/class/**").hasAnyAuthority("ADMIN", "TEACHER")
                        .requestMatchers(HttpMethod.GET, "/api/v1/class/**").hasAnyAuthority("ADMIN", "TEACHER")
                        .requestMatchers(HttpMethod.GET, "/api/v1/class").hasAnyAuthority("ADMIN")

                        .requestMatchers("/api/v1/department").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/v1/department/**").hasAnyAuthority("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/v1/subject").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/subject/**").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/subject/**").hasAnyAuthority("ADMIN")

                        .requestMatchers("/api/v1/exam").hasAnyAuthority("ADMIN", "TEACHER")
                        .requestMatchers("/api/v1/exam/**").hasAnyAuthority("ADMIN", "TEACHER")

                        .requestMatchers(HttpMethod.PUT, "/api/v1/teacher/**").hasAnyAuthority("ADMIN", "TEACHER")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/teacher/**").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/teacher/**").hasAnyAuthority("ADMIN", "TEACHER")
                        .requestMatchers(HttpMethod.GET, "/api/v1/teacher").hasAnyAuthority("ADMIN")

                        .requestMatchers(HttpMethod.PUT, "/api/v1/student/**").hasAnyAuthority("ADMIN", "STUDENT")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/student/**").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/student/**").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/student").hasAnyAuthority("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/v1/enroll").hasAnyAuthority("ADMIN", "STUDENT")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/enroll/**").hasAnyAuthority("ADMIN", "TEACHER", "STUDENT")
                        .requestMatchers(HttpMethod.GET, "/api/v1/enroll/**").hasAnyAuthority("ADMIN", "STUDENT")
                        .requestMatchers(HttpMethod.GET, "/api/v1/enroll").hasAnyAuthority("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/v1/result").hasAnyAuthority("ADMIN", "TEACHER")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/result/**").hasAnyAuthority("ADMIN", "TEACHER")
                        .requestMatchers(HttpMethod.GET, "/api/v1/result/**").hasAnyAuthority("ADMIN", "TEACHER")
                        .requestMatchers(HttpMethod.GET, "/api/v1/result").hasAnyAuthority("ADMIN", "TEACHER")

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
