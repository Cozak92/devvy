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
class UserController(private val userService: UserService) {


    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    fun getMyUserInfo(): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(userService.getMyUserWithAuthorities())
    }

    @PostMapping
    fun signup(@RequestBody @Valid userRequest: UserJoinRequest): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(userService.signup(userRequest));
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    fun getUserInfo(@PathVariable username: String): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(userService.getUserWithAuthorities(username))
    }


}