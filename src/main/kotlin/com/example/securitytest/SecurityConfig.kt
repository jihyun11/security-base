package com.example.securitytest

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests { security ->
            security
                .requestMatchers("/login").permitAll()
                .anyRequest().authenticated()
        }

        http.formLogin { form ->
            form.loginPage("/login")
                .defaultSuccessUrl("/")
                .loginProcessingUrl("/loginJihyun")
                .permitAll()
        }

        http.csrf { it.disable() }

        return http.build()
    }
}