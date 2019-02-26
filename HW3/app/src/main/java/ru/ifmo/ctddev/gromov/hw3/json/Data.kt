package ru.ifmo.ctddev.gromov.hw3.json

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("response")
class Data {
    @JsonProperty("response")
    @get:JsonProperty("response")
    var response: Response? = null
}
