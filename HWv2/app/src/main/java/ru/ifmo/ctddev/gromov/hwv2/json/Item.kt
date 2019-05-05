package ru.ifmo.ctddev.gromov.hwv2.json

import java.util.HashMap
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("id", "album_id", "owner_id", "user_id", "sizes", "text", "date", "post_id")
class Item {
    @JsonProperty("id")
    @get:JsonProperty("id")
    var id: Int? = null
    @JsonProperty("album_id")
    @get:JsonProperty("album_id")
    var albumId: Int? = null
    @JsonProperty("owner_id")
    @get:JsonProperty("owner_id")
    var ownerId: Int? = null
    @JsonProperty("user_id")
    @get:JsonProperty("user_id")
    var userId: Int? = null
    @JsonProperty("sizes")
    @get:JsonProperty("sizes")
    var sizes: List<Size>? = null
    @JsonProperty("text")
    @get:JsonProperty("text")
    var text: String? = null
    @JsonProperty("date")
    @get:JsonProperty("date")
    var date: Int? = null
    @JsonProperty("post_id")
    @get:JsonProperty("post_id")
    var post_id: Int? = null
    @JsonIgnore
    private val additionalProperties = HashMap<String, Any>()

    @JsonAnyGetter
    fun getAdditionalProperties(): Map<String, Any> = this.additionalProperties
}
