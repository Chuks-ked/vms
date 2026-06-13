package com.vms.vms.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity
            .csrf { csrf -> csrf.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .build()
    }

//    @Bean
//    fun securityFilterChain(
//        http: HttpSecurity
//    ): SecurityFilterChain {
//
//        http
//            .csrf { it.disable() }
//            .authorizeHttpRequests {
//
//                it.requestMatchers(
//                    "/api/v1/auth/**",
//                    "/swagger-ui/**",
//                    "/v3/api-docs/**"
//                ).permitAll()
//
//                it.anyRequest()
//                    .authenticated()
//            }
//
//        return http.build()
//    }

}