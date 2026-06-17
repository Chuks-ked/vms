package com.vms.vms.model.response

import java.time.LocalDate

data class DailyVisitReport (
    val visitDate: LocalDate,
    val totalVisits: Long
)