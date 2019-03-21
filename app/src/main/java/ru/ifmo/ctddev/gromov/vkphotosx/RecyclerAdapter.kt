package ru.ifmo.ctddev.gromov.vkphotosx

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(var posts: List<Post>) :
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(val text: TextView) : RecyclerView.ViewHolder(text)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.MyViewHolder =
        MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.post, parent, false) as TextView)

    override fun onBindViewHolder(holder: MyViewHolder, i: Int) = with(holder.text) {
        text = posts[i].text
        setBackgroundColor(if (posts[i].isFave) 1107296000 else -2236929)
        setOnClickListener {
            context.startActivity(Intent(context, DetailActivity::class.java).putExtra("POST", posts[i]))
        }
    }

    override fun getItemCount() = posts.size
}