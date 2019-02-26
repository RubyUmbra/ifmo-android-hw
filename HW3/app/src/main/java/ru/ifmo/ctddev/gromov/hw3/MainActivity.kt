package ru.ifmo.ctddev.gromov.hw3

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var binder: Loader.MyBinder? = null
    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            this@MainActivity.binder = (service as Loader.MyBinder).apply {
                setCallback(object : Loader.OnLoad {
                    override fun onLoad() = updateContent()
                })
            }
        }

        override fun onServiceDisconnected(name: ComponentName) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindService(Intent(this, Loader::class.java), serviceConnection, 0)
        if (checkNet()) {
            error_text.text = getString(R.string.error_disconnected)
            error_text.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        } else {
            setContentView(R.layout.activity_main)
            recyclerView.layoutManager = LinearLayoutManager(this)
            if (App.list == null) {
                Loader.loadPosts(this)
            } else updateContent()
        }
    }

    @SuppressLint("SetTextI18n")
    fun updateContent() = with(App.list?.response?.items) {
        if (this != null) {
            recyclerView.adapter = ItemRecyclerViewAdapter(this)
            recyclerView.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        } else {
            error_text.text = "ERROR"
            error_text.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        }
    }

    private fun checkNet(): Boolean =
        with(applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager) {
            activeNetworkInfo == null || !activeNetworkInfo.isConnected
        }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(serviceConnection)
    }
}
