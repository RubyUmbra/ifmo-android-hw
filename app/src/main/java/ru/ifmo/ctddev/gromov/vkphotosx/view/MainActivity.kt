package ru.ifmo.ctddev.gromov.vkphotosx.view

import android.os.Bundle
import android.util.Log
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
    lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm = ViewModelProviders.of(this).get(MainViewModel::class.java)
        recycler.adapter = RecyclerAdapter(emptyList())
//        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        search_button.setOnClickListener { vm.reloadList(search_field.text.toString()) }
        vm.data.observe(this, Observer {
            (recycler.adapter as RecyclerAdapter).apply { posts = it }.notifyDataSetChanged()
        })
    }
}