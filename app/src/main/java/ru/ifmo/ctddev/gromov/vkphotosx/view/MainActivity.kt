package ru.ifmo.ctddev.gromov.vkphotosx.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import ru.ifmo.ctddev.gromov.vkphotosx.R
import ru.ifmo.ctddev.gromov.vkphotosx.databinding.ActivityMainBinding
import ru.ifmo.ctddev.gromov.vkphotosx.viewmodel.MainViewModel
import ru.ifmo.ctddev.gromov.vkphotosx.viewmodel.RecyclerAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var viewmodel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewmodel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply { vm = viewmodel }
        recycler.adapter = RecyclerAdapter(emptyList())
        viewmodel.data.observe(this, Observer {
            (recycler.adapter as RecyclerAdapter).apply { posts = it }.notifyDataSetChanged()
        })
    }
}