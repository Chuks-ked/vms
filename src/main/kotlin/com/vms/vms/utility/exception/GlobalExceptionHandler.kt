package com.vms.vms.utility.exception

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFound(
        ex: ResourceNotFoundException,
        request : HttpServletRequest
    ): ResponseEntity<ErrorResponse> {

        val errorPayload = ErrorResponse (
            status = HttpStatus.NOT_FOUND.value(),
            error = "Resource not found",
            message = ex.message,
            path = request.requestURI
        )

        return ResponseEntity(errorPayload, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(DuplicateResourceException::class)
    fun handleDuplicateResource(
        ex: DuplicateResourceException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {

        val errorPayload = ErrorResponse(
            status = HttpStatus.CONFLICT.value(),
            error = "Resource Already Exists",
            message = ex.message,
            path = request.requestURI
        )

        return ResponseEntity(errorPayload, HttpStatus.CONFLICT)
    }

    @ExceptionHandler(AccessDeniedException::class)
    fun handleAccessDenied(
        ex: AccessDeniedException
    ): ResponseEntity<Map<String, String>> {

        return ResponseEntity
            .status(HttpStatus.FORBIDDEN)
            .body(
                mapOf(
                    "message" to ex.message!!
                )
            )
    }
}