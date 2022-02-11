package com.web.devvy.rest.controller.exceptions

class DuplicateMemberException : RuntimeException {
    constructor() : super() {}
    constructor(message: String, cause: Throwable) : super(message, cause) {}
    constructor(message: String) : super(message) {}
    constructor(cause: Throwable) : super(cause) {}
}