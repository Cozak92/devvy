package com.web.devvy.rest.controller.user
import com.web.devvy.rest.model.user.UserDto.*
import com.web.devvy.application.service.user.UserPort
import com.web.devvy.domain.command.user.SignUpUserCommand
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/user")
class UserController(private val userCustomService: UserPort) {

    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    fun getMyUserInfo(): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(userCustomService.getMyUserWithAuthorities())
    }

    @PostMapping
    fun signup(@RequestBody @Valid command: SignUpUserCommand): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(userCustomService.signup(command));
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    fun getUserInfo(@PathVariable username: String): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(userCustomService.getUserWithAuthorities(username))
    }


}