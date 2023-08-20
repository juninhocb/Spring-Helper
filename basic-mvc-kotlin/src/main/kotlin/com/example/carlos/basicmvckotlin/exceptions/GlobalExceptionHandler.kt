package com.example.carlos.basicmvckotlin.exceptions

import jakarta.servlet.http.HttpServletRequest
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFound(exception: ResourceNotFoundException,
                               httpRequest: HttpServletRequest) : ResponseEntity<ExceptionDto> {

        val exceptionDto = getExceptionDto(
                exception.message,
                httpRequest.requestURI,
                HttpStatus.NOT_FOUND.value())

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDto)

    }

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleDataIntegrityViolation(exception: DataIntegrityViolationException,
                                     httpRequest: HttpServletRequest) : ResponseEntity<ExceptionDto>{

        val exceptionDto = getExceptionDto(
                exception.message,
                httpRequest.requestURI,
                HttpStatus.BAD_REQUEST.value())

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDto)
    }

    fun getExceptionDto(msg: String?, path: String?, code: Int?) : ExceptionDto{
        val exceptionDto = ExceptionDto()
        exceptionDto.message = msg
        exceptionDto.path = path
        exceptionDto.responseCode = code
        exceptionDto.timestamp = LocalDateTime.now()

        return exceptionDto
    }
}