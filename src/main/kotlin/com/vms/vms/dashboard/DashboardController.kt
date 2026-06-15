package com.vms.vms.dashboard

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/dashboard")
class DashboardController(
    private val dashboardService: DashboardService
) {

    @GetMapping
    fun dashboard() = dashboardService.dashboard()

    @GetMapping("/daily")
    fun dailyReport() = dashboardService.visitsPerDay()

    @GetMapping("/status")
    fun statusReport() = dashboardService.visitsByStatus()
}
