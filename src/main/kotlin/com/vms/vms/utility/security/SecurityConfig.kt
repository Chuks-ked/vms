package com.vms.vms.utility.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfig(
    private val jwtAuthFilter: JwtAuthFilter
) {

    @Bean
    fun securityFilterChain(
        http: HttpSecurity
    ): SecurityFilterChain {

        http
            .csrf { it.disable() }

            .sessionManagement {
                it.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
                )
            }

            .authorizeHttpRequests {

                it.requestMatchers(
                    "/api/v1/auth/**",
                    "/swagger-ui/**",
                    "/v3/api-docs/**"
                ).permitAll()

                it.anyRequest()
                    .authenticated()
            }

            .addFilterBefore(
                jwtAuthFilter,
                UsernamePasswordAuthenticationFilter::class.java
            )

        return http.build()
    }
}