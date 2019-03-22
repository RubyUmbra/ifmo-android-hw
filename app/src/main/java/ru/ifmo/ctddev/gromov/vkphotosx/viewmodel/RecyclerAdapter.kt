package ru.ifmo.ctddev.gromov.vkphotosx.viewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ru.ifmo.ctddev.gromov.vkphotosx.R
import ru.ifmo.ctddev.gromov.vkphotosx.databinding.PostBinding
import ru.ifmo.ctddev.gromov.vkphotosx.model.room.Post

class RecyclerAdapter(var posts: List<Post>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    class ViewHolder(val binding: PostBinding) : RecyclerView.ViewHolder(binding.root)

    private fun RecyclerAdapter.ViewHolder.bind(p: Post) = binding.apply { post = p }.executePendingBindings()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.post, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, i: Int) = holder.bind(posts[i])

    override fun getItemCount() = posts.size
}