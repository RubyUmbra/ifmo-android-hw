package ru.ifmo.ctddev.gromov.hw3.json

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("type", "url", "width", "height")
class Size {
    @JsonProperty("type")
    @get:JsonProperty("type")
    var type: String? = null
    @JsonProperty("url")
    @get:JsonProperty("url")
    var url: String? = null
    @JsonProperty("width")
    @get:JsonProperty("width")
    var width: Int? = null
    @JsonProperty("height")
    @get:JsonProperty("height")
    var height: Int? = null
}
