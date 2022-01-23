package com.web.devvy.handler

import lombok.AccessLevel
import lombok.NoArgsConstructor
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import java.util.stream.Collectors


@NoArgsConstructor(access = AccessLevel.PROTECTED)
class ErrorResponse private constructor(code: ErrorCode, errors: List<FieldError>) {
    private val message: String
    private val status: Int
    private val errors: List<FieldError>

    init {
        message = code.message
        status = code.status
        this.errors = errors
    }

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    class FieldError private constructor(private val field: String, private val value: String) {
        companion object {
            fun of(bindingResult: BindingResult): List<FieldError> {
                val fieldErrors = bindingResult.fieldErrors
                return fieldErrors.stream()
                    .map { error: org.springframework.validation.FieldError ->
                        FieldError(
                            error.field,
                            if (error.rejectedValue == null) "" else error.rejectedValue.toString()
                        )
                    }
                    .collect(Collectors.toList())
            }
        }
    }

    companion object {
        fun of(code: ErrorCode, bindingResult: BindingResult): ErrorResponse {
            return ErrorResponse(code, FieldError.of(bindingResult))
        }
    }
}