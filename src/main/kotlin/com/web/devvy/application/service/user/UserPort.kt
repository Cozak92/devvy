package com.web.devvy.application.service.user

import com.web.devvy.rest.model.user.UserDto

interface UserPort {

    fun getMyUserWithAuthorities(): UserDto.UserResponse?
    fun signup(UserJoinRequest: UserDto.UserJoinRequest): UserDto.UserResponse?
    fun getUserWithAuthorities(userName: String): UserDto.UserResponse?
}