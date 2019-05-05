package ru.ifmo.ctddev.gromov.hwv2

import android.os.AsyncTask
import com.fasterxml.jackson.databind.ObjectMapper
import ru.ifmo.ctddev.gromov.hwv2.json.Data
import java.lang.ref.WeakReference
import java.net.URL

class TakeListTask(private val activity: WeakReference<MainActivity>) : AsyncTask<Void, Void, Data>() {
    override fun doInBackground(vararg params: Void?): Data? = ObjectMapper().readValue(URL(url).openConnection().run {
        App.logNet("used")
        connect()
        getInputStream().bufferedReader().readLines().joinToString("")
    }, Data::class.java)

    override fun onPostExecute(result: Data?) {
        super.onPostExecute(result)
        App.logNet("downloaded ${result?.response?.items?.size} elements")
        App.getApp().list = result
        activity.get()?.updateContent()
    }

    private companion object {
        private const val site = "https://api.vk.com/method/photos.search"
        private const val q = "landscape%20Fjord"
        private const val count = "25"
        private const val access_token = "7b4070287b4070287b4070289c7b26c24477b407b407028209043d6e20082bb38b48bd7"
        private const val v = "5.92"
        private const val url = "$site?q=$q&count=$count&v=$v&access_token=$access_token"
    }
}
