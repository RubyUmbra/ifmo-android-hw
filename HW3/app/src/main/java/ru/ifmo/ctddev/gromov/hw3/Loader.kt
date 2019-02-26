package ru.ifmo.ctddev.gromov.hw3

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import ru.ifmo.ctddev.gromov.hw3.json.Data
import ru.ifmo.ctddev.gromov.hw3.json.Item
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.net.URL

class Loader : IntentService("Loader") {
    private var old: Pair<Int, Bitmap>? = null
    private val main = Handler(Looper.getMainLooper())
    private var callback: OnLoad? = null

    override fun onHandleIntent(intent: Intent?) {
        if (intent != null) when {
            intent.action == ID_PIC_ -> loadPictureH(intent.getIntExtra(ID_, -1))
            intent.action == ID_LIST -> loadPostsH()
            else -> Unit
        }
    }

    override fun onBind(intent: Intent): IBinder? = MyBinder(this)

    override fun onUnbind(intent: Intent): Boolean {
        callback = null
        return super.onUnbind(intent)
    }

    private fun loadPostsH() {
        if (App.list == null) App.list = App.getMapper().readValue(URL(App.getUrl()).openConnection().run {
            App.logNet("used")
            connect()
            getInputStream().bufferedReader().readLines().joinToString("")
        }, Data::class.java)
        main.post { callback?.onLoad() }
    }

    private fun loadPictureH(index: Int) {
        val res = if (index >= 0) ((if (old != null && old!!.first == index) old!!.second else downloadBitmap(index)))
        else null
        if (callback != null) {
            old = null
            main.post { callback?.onLoad(res) }
        } else {
            old = if (res != null) Pair(index, res) else null
        }
    }

    private fun downloadBitmap(index: Int): Bitmap? {
        var result: Bitmap? = null
        try {
            val item: Item = with(App.list?.response?.items)
            { if (this == null) null else this[index] } ?: return result
            val id = item.id
            val cDir: File = cacheDir ?: return null
            val pics: Array<String> = cDir.list()
            if (pics.contains(id.toString())) {
                App.logNet("not used for $id")
                val picFile = File(cDir.path, "$id")
                val ifs = FileInputStream(picFile)
                result = BitmapFactory.decodeStream(ifs)
                ifs.close()
            } else {
                val url: String? = item.sizes?.last()?.url
                result = BitmapFactory.decodeStream(URL(url).openConnection().run {
                    App.logNet("used for $id")
                    connect()
                    getInputStream()
                })
                val pic = File(cDir, "$id")
                val fsout = FileOutputStream(pic)
                result?.compress(Bitmap.CompressFormat.PNG, 85, fsout)
                fsout.flush()
                fsout.close()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return result
    }

    interface OnLoad {
        fun onLoad(data: Bitmap?) {}
        fun onLoad() {}
    }

    class MyBinder(private val service: Loader? = null) : Binder() {
        fun setCallback(callback: OnLoad) = Handler(Looper.getMainLooper()).post { service?.callback = callback }
    }

    companion object {
        private const val ID_LIST = "posts"
        private const val ID_ = "PIC_ID"
        private const val ID_PIC_ = "picture"

        fun loadPosts(context: Context) {
            context.startService(Intent(context, Loader::class.java).apply { action = ID_LIST })
        }

        fun loadPicture(context: Context, id: Int) {
            context.startService(Intent(context, Loader::class.java).apply {
                action = ID_PIC_
                putExtra(ID_, id)
            })
        }
    }
}
