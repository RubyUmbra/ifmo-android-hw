package ru.ifmo.ctddev.gromov.vkphotos

import android.app.Activity
import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.os.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.net.URL

class Loader : IntentService("Loader") {
    override fun onHandleIntent(intent: Intent) {
        val url = intent.getStringExtra("EXTRA_URL")
        (intent.getParcelableExtra("RECEIVER") as ResultReceiver).send(Activity.RESULT_OK, Bundle().apply {
            putString("FILENAME", "img" + url.hashCode().toString())
            putByteArray("RESULT_VALUE", loadByteArray(url))
        })
    }

    private fun loadByteArray(url: String): ByteArray? {
        var result: ByteArray? = null
        try {
            result = if (cacheDir.list().contains("img" + url.hashCode().toString()))
                File(cacheDir.path, "img" + url.hashCode().toString()).readBytes()
            else URL(url).openConnection().run {
                connect()
                val buf = ByteArrayOutputStream()
                getInputStream().copyTo(buf)
                buf.toByteArray()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return result
    }

    companion object {
        fun loadList(context: Context, url: String, receiver: ServiceReceiver) {
            context.startService(Intent(context, Loader::class.java).apply {
                putExtra("EXTRA_URL", url)
                putExtra("RECEIVER", receiver)
            })
        }
    }
}
