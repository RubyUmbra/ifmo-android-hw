package ru.ifmo.ctddev.gromov.hwv2

import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.os.AsyncTask
import java.lang.ref.WeakReference
import java.net.URL

class TakeImageTask(private val activity: WeakReference<DetailActivity>) : AsyncTask<String, Void, Bitmap>() {
    override fun doInBackground(vararg params: String?): Bitmap =
        BitmapFactory.decodeStream(URL(params[0]).openConnection().run {
            App.logNet("used")
            connect()
            getInputStream()
        })

    override fun onPostExecute(result: Bitmap?) {
        super.onPostExecute(result)
        activity.get()?.apply {
            bm = result
            updateContent()
        }
    }
}
