package com.web.devvy.domain.command.user

import com.web.devvy.domain.vo.user.*

import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

class SignUpUserCommand(
    @field:NotEmpty val name: Name?,
    @field:NotEmpty val password: UserPassword?,
    @field:NotEmpty val username: Username?,
    @field:Email val email: UserEmail?,
)
