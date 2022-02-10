package com.web.devvy.services.user

import com.web.devvy.dto.user.UserDto

interface UserPort {

    fun getMyUserWithAuthorities(): UserDto.UserResponse?
    fun signup(UserJoinRequest: UserDto.UserJoinRequest): UserDto.UserResponse?
    fun getUserWithAuthorities(userName: String): UserDto.UserResponse?
}