package com.web.devvy.application.service.user

import com.web.devvy.domain.command.user.SignUpUserCommand
import com.web.devvy.rest.model.user.UserDto

interface UserPort {
    fun getMyUserWithAuthorities(): UserDto.UserResponse?
    fun signup(signUpUserCommand: SignUpUserCommand): UserDto.UserResponse?
    fun getUserWithAuthorities(userName: String): UserDto.UserResponse?
}