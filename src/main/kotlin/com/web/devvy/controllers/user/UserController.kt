package com.web.devvy.controllers.user

import com.web.devvy.Dto.User.UserDto.*
import com.web.devvy.Dto.User.UserDto.UserJoinRequest
import com.web.devvy.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/user")
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    fun getMyUserInfo(): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(userService.getMyUserWithAuthorities())
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    fun getUserInfo(@PathVariable username: String): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(userService.getUserWithAuthorities(username))
    }

    @PostMapping
    fun signup(@RequestBody @Valid userRequest: UserJoinRequest): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(userService.signup(userRequest));
    }

}