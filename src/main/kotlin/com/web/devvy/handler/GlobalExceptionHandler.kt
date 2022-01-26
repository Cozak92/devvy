package com.web.devvy.handler

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest


@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        val errors = mutableListOf<Error>()
        e.bindingResult.allErrors.forEach { errorObject ->
            val error = Error().apply {
                field = (errorObject as FieldError).field
                message = errorObject.defaultMessage
                value = errorObject.rejectedValue
            }
            errors.add(error)
        }
        val errorResponse = ErrorResponse().apply {
            resultCode = "FAIL"
            httpStatus = HttpStatus.BAD_REQUEST.value().toString()
            httpMethod = request.method
            message = "요청에 에러가 발생하였습니다."
            path = request.requestURI.toString()
            timestamp = LocalDateTime.now()
            this.errors = errors
        }


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }
}