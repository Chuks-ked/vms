package com.vms.vms.visitor

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "visitors")
class Visitor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    var fullName: String,

    @Column(nullable = false)
    var email: String,

    @Column(nullable = false)
    var phoneNumber: String,

    var company: String? = null,

    var identificationType: String? = null,

    var identificationNumber: String? = null,

    val createdAt: LocalDateTime = LocalDateTime.now(),
 )