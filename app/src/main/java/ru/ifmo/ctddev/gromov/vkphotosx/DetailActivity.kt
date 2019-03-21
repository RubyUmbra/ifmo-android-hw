package ru.ifmo.ctddev.gromov.vkphotosx

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
    private lateinit var post: Post

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        post = intent.getParcelableExtra("POST")
        Picasso.get().load(post.url).into(image)
        fave_button.setOnClickListener { update(true) }
        unfave_button.setOnClickListener { update(false) }
    }

    private fun update(fave: Boolean) {
        if (post.isFave != fave) GlobalScope.launch { App.db.postDAO().update(post.apply { isFave = fave }) }
    }
}