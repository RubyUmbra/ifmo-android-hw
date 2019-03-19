package ru.ifmo.ctddev.gromov.vkphotos

import android.app.Activity
import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.os.*
import java.io.ByteArrayOutputStream
import java.net.URL

class Loader : IntentService("Loader") {
    override fun onHandleIntent(intent: Intent) {
        val url = intent.getStringExtra("EXTRA_URL")
        val receiver = intent.getParcelableExtra("RECEIVER") as ResultReceiver
        receiver.send(Activity.RESULT_OK, Bundle().apply {
            putString("FILENAME", "img" + url.hashCode().toString())
            putByteArray("RESULT_VALUE", loadByteArray(url))
        })
    }

    private fun loadByteArray(url: String?): ByteArray = URL(url).openConnection().run {
        connect()
        val buf = ByteArrayOutputStream()
        getInputStream().copyTo(buf)
        buf.toByteArray()
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
