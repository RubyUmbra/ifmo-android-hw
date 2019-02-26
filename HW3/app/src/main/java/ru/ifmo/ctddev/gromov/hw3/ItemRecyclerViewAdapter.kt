package ru.ifmo.ctddev.gromov.hw3

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.list_item.view.*
import ru.ifmo.ctddev.gromov.hw3.json.Item

class ItemRecyclerViewAdapter(private val values: List<Item>) :
    RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.contentView.text = item.text
        with(holder.itemView) {
            tag = item
            setOnClickListener { v ->
                v.context.startActivity(Intent(v.context, DetailActivity::class.java).apply {
                    putExtra("item", holder.adapterPosition)
                })
            }
        }
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val contentView: TextView = view.content
    }
}
