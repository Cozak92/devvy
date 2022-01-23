package com.web.devvy.handler

enum class ErrorCode(val status: Int, val message: String) {
    INVALID_INPUT_VALUE(400, "INVALID INPUT VALUE");
}