package com.vms.vms.visit.dto

import com.vms.vms.visit.VisitStatus
import java.time.LocalDate

data class VisitResponse(
    val id: Long,

    val visitorName: String,

    val hostName: String,

    val purpose: String,

    val visitDate: LocalDate,

    val status: VisitStatus
)