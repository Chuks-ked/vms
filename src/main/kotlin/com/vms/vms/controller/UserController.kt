package com.vms.vms.controller

import com.vms.vms.service.UserService
import com.vms.vms.model.request.CreateUserRequest
import com.vms.vms.model.request.UpdateUserRequest
import com.vms.vms.model.response.UserResponse
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userService: UserService
) {

    @PostMapping
    fun createUser(
        @Valid
        @RequestBody request: CreateUserRequest,
    ): UserResponse {
        return userService.createUser(request)
    }

    @GetMapping
    fun getAllUsers() = userService.getAllUsers()

    @GetMapping("/{id}")
    fun getUser(
        @PathVariable id: Long
    ) = userService.getUser(id)

    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable id: Long,
        @Valid
        @RequestBody request: UpdateUserRequest,
    ) = userService.updateUser(id, request)

    @DeleteMapping("/{id}")
    fun deleteUser(
        @PathVariable id: Long
    ) = userService.deleteUser(id)

}