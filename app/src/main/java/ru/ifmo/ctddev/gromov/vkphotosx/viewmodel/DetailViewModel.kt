package ru.ifmo.ctddev.gromov.vkphotosx.viewmodel

import android.widget.ImageView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import ru.ifmo.ctddev.gromov.vkphotosx.model.App
import ru.ifmo.ctddev.gromov.vkphotosx.model.room.Post

class DetailViewModel(val post: Post) : ViewModel() {
    fun loadImage(image: ImageView): Unit = Picasso.get().load(post.url).into(image)
    fun update(fave: Boolean) = if (post.isFave != fave) App.updateData(post.apply { isFave = fave }) else Unit
    class ModelFactory(private val post: Post) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = DetailViewModel(post) as T
    }
}