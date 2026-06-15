package com.vms.vms.dashboard.dto

import java.time.LocalDate

data class DailyVisitReport (
    val visitDate: LocalDate,
    val totalVisits: Long
)