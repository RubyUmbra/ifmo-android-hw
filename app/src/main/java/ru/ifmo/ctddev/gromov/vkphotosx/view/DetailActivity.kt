package ru.ifmo.ctddev.gromov.vkphotosx.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import ru.ifmo.ctddev.gromov.vkphotosx.R
import ru.ifmo.ctddev.gromov.vkphotosx.model.App
import ru.ifmo.ctddev.gromov.vkphotosx.model.room.Post
import ru.ifmo.ctddev.gromov.vkphotosx.viewmodel.DetailViewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var post: Post

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        post = intent.getParcelableExtra("POST")

        val model: DetailViewModel = ViewModelProviders.of(this).get(post.id.toString(), DetailViewModel::class.java)


        Picasso.get().load(post.url).into(image)
        fave_button.setOnClickListener { update(true) }
        unfave_button.setOnClickListener { update(false) }
    }

    private fun update(fave: Boolean) {
        if (post.isFave != fave) App.updateData(post.apply { isFave = fave })
    }
}