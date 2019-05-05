package ru.ifmo.ctddev.gromov.hwv2

import android.app.Application
import android.graphics.Bitmap
import android.util.Log
import ru.ifmo.ctddev.gromov.hwv2.json.Data

class App : Application() {
    companion object {
        private val app = App()
        private const val tagWIFI = "WIFI"
        fun getApp() = app
        fun logNet(message: String) = Log.d(App.tagWIFI, message)
    }

    var list: Data? = null
    var images: MutableMap<Int, Bitmap> = mutableMapOf()
}
