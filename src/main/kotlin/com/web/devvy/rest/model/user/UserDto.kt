package com.web.devvy.rest.model.user

import com.web.devvy.infrastructure.persistence.entity.Authority
import com.web.devvy.infrastructure.persistence.entity.User
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

class UserDto {
    data class UserJoinRequest(
            @field:NotEmpty val name: String?,
            @field:NotEmpty val password: String?,
            @field:NotEmpty val username: String?,
            @field:Email val email: String?,
            val is_deleted: Boolean?,
    )


    data class UserResponse(
        @field:NotEmpty val name: String,
        @field:NotEmpty val username: String,
        @field:Email val email: String,
        @field:NotEmpty val authorities: Set<Authority>,
    ) {
        companion object {
            fun from(user: User?): UserResponse? {
                return user?.let {
                    UserResponse(
                            name = user.name,
                            username = user.username,
                            email = user.email,
                            authorities = user.authorities
                    )
                }
            }
        }


    }

    data class LoginRequest(@field:NotEmpty val username: String, @field:NotEmpty var password: String)
}
