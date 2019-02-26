package ru.ifmo.ctddev.gromov.hw3.json

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("count", "items")
class Response {
    @JsonProperty("count")
    @get:JsonProperty("count")
    var count: Int? = null
    @JsonProperty("items")
    @get:JsonProperty("items")
    var items: List<Item>? = null
}
