package com.web.devvy.controllers.user

import com.web.devvy.Dto.User.UserDto
import com.web.devvy.Dto.token.TokenDto
import com.web.devvy.jwt.JwtFilter
import com.web.devvy.jwt.TokenProvider
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid


@RestController
@RequestMapping("/api")
class AuthController(
    private val tokenProvider: TokenProvider,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder
) {
    @PostMapping("/authenticate")
    fun authorize(@RequestBody @Valid LoginRequest: UserDto.LoginRequest): ResponseEntity<TokenDto.Token> {
        val authenticationToken = UsernamePasswordAuthenticationToken(LoginRequest.username, LoginRequest.password)
        val authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken)
        SecurityContextHolder.getContext().authentication = authentication
        val jwt = tokenProvider.createToken(authentication)
        val httpHeaders = HttpHeaders()
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer $jwt")
        return ResponseEntity(TokenDto.Token(jwt), httpHeaders, HttpStatus.OK)
    }
}