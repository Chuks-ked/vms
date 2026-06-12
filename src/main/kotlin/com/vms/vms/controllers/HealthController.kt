package com.vms.vms.controllers

import com.vms.vms.common.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class HealthController {

    @GetMapping("/health")
    fun health() =
        ApiResponse(
            success = true,
            message = "VMS is Healthy",
            data = ""
        )
}