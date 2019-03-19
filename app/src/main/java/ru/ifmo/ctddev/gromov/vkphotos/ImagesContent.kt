package ru.ifmo.ctddev.gromov.vkphotos

import android.content.Context
import android.widget.ImageView
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.stream.JsonReader
import com.squareup.picasso.Picasso
import java.io.File
import java.io.InputStreamReader
import java.util.*
import kotlin.math.min

object ImagesContent {
    private const val SITE = "https://api.vk.com/method/photos.search"
    private const val Q = "landscape%20Fjord"
    private const val COUNT = 25
    private const val ACCESS_TOKEN = "7b4070287b4070287b4070289c7b26c24477b407b407028209043d6e20082bb38b48bd7"
    private const val V = "5.92"
    private const val URL = "$SITE?q=$Q&count=$COUNT&v=$V&access_token=$ACCESS_TOKEN"

    val IMAGES: MutableList<ImageItem> = ArrayList()
    val IMAGE_MAP: MutableMap<Int, ImageItem> = HashMap()

    fun loadJson(context: Context, receiver: ServiceReceiver): Unit = Loader.loadList(context, URL, receiver)

    fun parse(data: ByteArray?) {
        val images = (JsonParser().parse(JsonReader(InputStreamReader(data?.inputStream()))) as JsonObject)
            .getAsJsonObject("response").getAsJsonArray("items")
        for (i in 0..min(images.size(), COUNT - 1)) addItem(ImageItem(images[i].asJsonObject))
    }

    private fun addItem(item: ImageItem) {
        IMAGES.add(item)
        IMAGE_MAP[item.id] = item
    }

    data class ImageItem(val id: Int, val description: String, val url: String) {
        constructor(o: JsonObject) : this(
            o.get("id").asInt,
            o.get("text").asString,
            o.getAsJsonArray("sizes").last().asJsonObject.get("url").asString
        )
    }

    fun setImageIntoView(context: Context?, url: String?, view: ImageView): Boolean {
        val filename = "img" + url?.hashCode().toString()
        return if (context?.fileList()?.contains(filename) == true) {
            Picasso.get().load(File(context.filesDir, filename)).into(view)
            true
        } else false
    }
}
