package ru.ifmo.ctddev.gromov.vkphotosx.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_detail.*
import ru.ifmo.ctddev.gromov.vkphotosx.R
import ru.ifmo.ctddev.gromov.vkphotosx.viewmodel.DetailViewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var vm: DetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        vm = ViewModelProviders.of(this, DetailViewModel.ModelFactory(intent.getParcelableExtra("POST")))
            .get(DetailViewModel::class.java)
        vm.loadImage(image)
        fave_button.setOnClickListener { vm.update(true) }
        unfave_button.setOnClickListener { vm.update(false) }
    }
}