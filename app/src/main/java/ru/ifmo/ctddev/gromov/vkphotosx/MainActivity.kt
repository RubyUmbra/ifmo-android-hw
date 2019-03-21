package ru.ifmo.ctddev.gromov.vkphotosx

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var data: LiveData<List<Post>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler.adapter = RecyclerAdapter(emptyList())
        data = App.db.postDAO().all()
        data.observe(this, Observer {
            (recycler.adapter as RecyclerAdapter).apply { posts = it }.notifyDataSetChanged()
        })
        search_button.setOnClickListener { App.instance.reloadList(search_field.text.toString()) }
    }

    override fun onDestroy() {
        super.onDestroy()
        data.removeObservers(this)
    }
}