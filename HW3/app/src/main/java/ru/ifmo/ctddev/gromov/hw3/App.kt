package ru.ifmo.ctddev.gromov.hw3

import android.app.Application
import android.util.Log
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import ru.ifmo.ctddev.gromov.hw3.json.Data

class App : Application() {
    companion object {
        private const val site = "https://api.vk.com/method/photos.search"
        private const val q = "landscape%20Fjord"
        private const val count = "25"
        private const val access_token = "7b4070287b4070287b4070289c7b26c24477b407b407028209043d6e20082bb38b48bd7"
        private const val v = "5.92"
        private const val url = "$site?q=$q&count=$count&v=$v&access_token=$access_token"
        fun getUrl() = url

        private val mapper: ObjectMapper =
            ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        fun getMapper() = mapper

        var list: Data? = null

        private const val tagWEB = "INTERNET_TAG"
        fun logNet(message: String) = Log.d(App.tagWEB, message)
    }
}
