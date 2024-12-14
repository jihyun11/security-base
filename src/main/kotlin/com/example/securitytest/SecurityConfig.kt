package com.example.securitytest

import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer
import org.springframework.security.web.SecurityFilterChain


@Configuration
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests { security ->
            security
                .requestMatchers("/login").permitAll()
                .requestMatchers(PathRequest.toH2Console()).permitAll()
                .anyRequest().authenticated()
        }

        http.formLogin { form ->
            form.loginPage("/login")
                .defaultSuccessUrl("/")
                .loginProcessingUrl("/login/jihyun")
                .permitAll()
        }

        http.logout { form ->
           form.logoutUrl("/logout")
               .logoutSuccessUrl("/login")
        }

        http.csrf { it.disable() }

        // ifram 해제 코드
        http.headers { securityHeadersConfigurer -> securityHeadersConfigurer.frameOptions {
                frameOptionsConfig -> frameOptionsConfig.sameOrigin() }
        }
        return http.build()
    }
}