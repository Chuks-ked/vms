package com.vms.vms.model.response

import com.vms.vms.utility.VisitStatus

data class VisitStatusReport (
    val status: VisitStatus,
    val total: Long
)