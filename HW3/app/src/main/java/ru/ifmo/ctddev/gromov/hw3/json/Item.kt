package ru.ifmo.ctddev.gromov.hw3.json

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("id", "sizes", "text", "date")
class Item {
    @JsonProperty("id")
    @get:JsonProperty("id")
    var id: Int? = null
    @JsonProperty("sizes")
    @get:JsonProperty("sizes")
    var sizes: List<Size>? = null
    @JsonProperty("text")
    @get:JsonProperty("text")
    var text: String? = null
    @JsonProperty("date")
    @get:JsonProperty("date")
    var date: Int? = null
}
