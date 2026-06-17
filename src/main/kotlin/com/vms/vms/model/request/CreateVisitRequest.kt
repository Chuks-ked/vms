package com.vms.vms.model.request

import jakarta.validation.constraints.FutureOrPresent
import jakarta.validation.constraints.NotBlank
import java.time.LocalDate

data class CreateVisitRequest(
    val visitorId: Long,

    val hostId: Long,

    @field:NotBlank
    val purpose: String,

    @field:FutureOrPresent
    val visitDate: LocalDate
)