package com.web.devvy.rest.model.user

import com.web.devvy.domain.entity.Authority
import com.web.devvy.domain.entity.User
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

class UserDto {


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
                        name = user.name.value,
                        username = user.username.value,
                        email = user.email.value,
                        authorities = user.authorities.value
                    )
                }
            }
        }


    }

    data class LoginRequest(@field:NotEmpty val username: String, @field:NotEmpty var password: String)
}
