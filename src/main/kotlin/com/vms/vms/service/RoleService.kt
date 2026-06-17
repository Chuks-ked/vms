package com.vms.vms.service

import com.vms.vms.utility.exception.DuplicateResourceException
import com.vms.vms.utility.exception.ResourceNotFoundException
import com.vms.vms.model.entity.Role
import com.vms.vms.repository.RoleRepository
import com.vms.vms.model.request.CreateRoleRequest
import com.vms.vms.model.response.RoleResponse
import com.vms.vms.model.request.UpdateRoleRequest
import org.springframework.stereotype.Service

@Service
class RoleService(
    private val roleRepository: RoleRepository
) {

    fun createRole(
        request: CreateRoleRequest
    ): RoleResponse {
        if (roleRepository.existsByRoleName(request.roleName)) {
            throw DuplicateResourceException("Role name already exists")
        }

        val role = roleRepository.save(
            Role(
                roleName = request.roleName,
                description = request.description,
            )
        )

        return RoleResponse(
            id = role.id,
            roleName = request.roleName,
            description = request.description,
        )
    }

    fun getAllRoles(): List<RoleResponse> {
        return roleRepository.findAll().map {
            RoleResponse(
                id = it.id,
                roleName = it.roleName,
                description = it.description,
            )
        }
    }

    fun getRole(id: Long): RoleResponse {
        val role = roleRepository.findById(id)
            .orElseThrow {
                ResourceNotFoundException(
                    "Role not found"
                )
            }

        return RoleResponse(
            id = role.id,
            roleName = role.roleName,
            description = role.description,
        )
    }

    fun updateRole(
        id: Long,
        request: UpdateRoleRequest
    ): RoleResponse {
        val role = roleRepository.findById(id)
            .orElseThrow {
                ResourceNotFoundException(
                    "Role with not found"
                )
            }

        role.roleName = request.roleName
        role.description = request.description

        val updated = roleRepository.save(role)

        return RoleResponse(
            id = updated.id,
            roleName = request.roleName,
            description = request.description,
        )
    }

    fun deleteRole(id: Long) {
        val role = roleRepository.findById(id)
            .orElseThrow {
                ResourceNotFoundException(
                    "Role with not found"
                )
            }
        roleRepository.delete(role)
    }


}