package com.example.carlos.basicmvckotlin.exceptions

class ResourceNotFoundException(msg: String) : RuntimeException("Resource not found$msg") {
}
