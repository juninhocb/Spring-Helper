package com.example.carlos.basicmvckotlin.exceptions

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

class ExceptionDto {
    var message: String? = null
    var path: String? = null
    @JsonProperty("code")
    var responseCode: Int? = null
    var timestamp: LocalDateTime? = null
}