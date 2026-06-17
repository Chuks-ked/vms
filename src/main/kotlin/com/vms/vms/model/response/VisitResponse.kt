package com.vms.vms.model.response

import com.vms.vms.utility.VisitStatus
import java.time.LocalDate

data class VisitResponse(
    val id: Long,

    val visitorName: String,

    val hostName: String,

    val purpose: String,

    val visitDate: LocalDate,

    val status: VisitStatus
)