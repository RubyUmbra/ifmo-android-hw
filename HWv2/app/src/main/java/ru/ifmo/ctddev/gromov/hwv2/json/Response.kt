package ru.ifmo.ctddev.gromov.hwv2.json

import java.util.HashMap
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonIgnore
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
    @JsonIgnore
    private val additionalProperties = HashMap<String, Any>()

    @JsonAnyGetter
    fun getAdditionalProperties(): Map<String, Any> = this.additionalProperties
}
