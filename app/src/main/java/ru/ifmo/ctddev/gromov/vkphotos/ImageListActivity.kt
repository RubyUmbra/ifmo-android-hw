package ru.ifmo.ctddev.gromov.vkphotos

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_image_list.*
import kotlinx.android.synthetic.main.image_list.*
import kotlinx.android.synthetic.main.image_list_content.view.*

class ImageListActivity : AppCompatActivity() {
    private var twoPane: Boolean = false
    lateinit var receiver: ServiceReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_list)
        setSupportActionBar(toolbar)
        toolbar.title = title

        if (image_detail_container != null) twoPane = true
        setupServiceReceiver()
        if (ImagesContent.IMAGES.size == 0) ImagesContent.loadJson(this, receiver)
        else setupRecyclerView(image_list)
    }

    private fun setupServiceReceiver() {
        receiver = ServiceReceiver(Handler())
        receiver.setReceiver(object : ServiceReceiver.Receiver {
            override fun onReceiveResult(resultCode: Int, resultData: Bundle) {
                if (resultCode == Activity.RESULT_OK) {
                    ImagesContent.parse(resultData.getByteArray("RESULT_VALUE"))
                    setupRecyclerView(image_list)
                }
            }
        })
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecyclerViewAdapter(this, ImagesContent.IMAGES, twoPane)
    }

    class RecyclerViewAdapter(
        private val parentActivity: ImageListActivity,
        private val values: List<ImagesContent.ImageItem>,
        private val twoPane: Boolean
    ) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
        private val onClickListener: View.OnClickListener = View.OnClickListener { v ->
            val item = v.tag as ImagesContent.ImageItem
            if (twoPane) parentActivity.supportFragmentManager.beginTransaction()
                .replace(R.id.image_detail_container, ImageDetailFragment().apply {
                    arguments = Bundle().apply {
                        putInt("item_id", item.id)
                        putString("EXTRA_URL", item.url)
                    }
                }).commit()
            else v.context.startActivity(Intent(v.context, ImageDetailActivity::class.java).apply {
                putExtra("item_id", item.id)
                putExtra("EXTRA_URL", item.url)
            })
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.image_list_content, parent, false))

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(values[position])
            with(holder.itemView) {
                tag = values[position]
                setOnClickListener(onClickListener)
            }
        }

        override fun getItemCount() = values.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            fun bind(item: ImagesContent.ImageItem) {
                contentView.text = item.description
            }

            private val contentView: TextView = view.content
        }
    }
}
