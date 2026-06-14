package com.vms.vms.visit.dto

import com.vms.vms.visit.VisitStatus

data class UpdateVisitStatusRequest(
    val status: VisitStatus
)