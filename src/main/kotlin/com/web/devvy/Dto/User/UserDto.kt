package com.web.devvy.Dto.User

import org.springframework.http.HttpStatus
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

class UserDto {
    data class UserRequest(@field:NotEmpty val name: String, @field:Email val email: String)
    data class UserResponse(val returnCode: HttpStatus, val returnMessage: String)
}