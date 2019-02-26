package ru.ifmo.ctddev.gromov.hw3

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Bitmap
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    private var bitmap: Bitmap? = null
    private var binder: Loader.MyBinder? = null
    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            this@DetailActivity.binder = (service as Loader.MyBinder).apply {
                setCallback(object : Loader.OnLoad {
                    override fun onLoad(data: Bitmap?) {
                        bitmap = data
                        updateContent()
                    }
                })
            }
        }

        override fun onServiceDisconnected(name: ComponentName) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        bindService(Intent(this, Loader::class.java), serviceConnection, 0)
        val index: Int? = intent?.extras?.getInt("item")
        if (index != null) {
            Loader.loadPicture(this, index)
        } else updateContent()
    }

    fun updateContent() {
        if (bitmap == null) {
            detailText.text = getString(R.string.error_message)
            detailText.visibility = View.VISIBLE
        } else {
            image.setImageBitmap(bitmap)
            image.visibility = View.VISIBLE
        }
        progressBar.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(serviceConnection)
    }
}
