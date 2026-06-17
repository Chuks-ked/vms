package com.vms.vms.utility.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthFilter(
    private val jwtService: JwtService,
    private val customUserDetailsService: CustomUserDetailsService,
): OncePerRequestFilter() {

    override  fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ){
        val authHeader = request.getHeader("Authorization")
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            val token = authHeader.removePrefix("Bearer ")

            if(jwtService.validateToken(token)){
                val email = jwtService.extractEmail(token)
                val userDetails = customUserDetailsService.loadUserByUsername(email)
                val auth = UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.authorities
                )
                SecurityContextHolder
                    .getContext()
                    .authentication = auth
            }
        }
        filterChain.doFilter(request, response)
    }
}