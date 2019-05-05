package ru.ifmo.ctddev.gromov.hwv2

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import ru.ifmo.ctddev.gromov.hwv2.json.Item
import java.lang.ref.WeakReference
import kotlinx.android.synthetic.main.list_item.view.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = LinearLayoutManager(this)
        if ((applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo == null
            || !(applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo.isConnected
        ) {
            error_text.text = getString(R.string.error_disconnected)
            progressBar.visibility = View.INVISIBLE
            error_text.visibility = View.VISIBLE
        } else {
            if (App.getApp().list == null) TakeListTask(WeakReference(this)).execute()
            else updateContent()
        }
    }

    fun updateContent() {
        recyclerView.adapter = ItemRecyclerViewAdapter(App.getApp().list?.response?.items!!)
        progressBar.visibility = View.INVISIBLE
        recyclerView.visibility = View.VISIBLE
    }

    class ItemRecyclerViewAdapter(private val values: List<Item>) :
        RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
            return ViewHolder(view)
        }

        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = values[position]
            holder.idView.text = "${position + 1}  "
            holder.contentView.text = item.text
            with(holder.itemView) {
                tag = item
                setOnClickListener { v ->
                    v.context.startActivity(Intent(v.context, DetailActivity::class.java).apply {
                        putExtra("item", position)
                    })
                }
            }
        }

        override fun getItemCount() = values.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val idView: TextView = view.id_text
            val contentView: TextView = view.content
        }
    }
}
