package ru.ifmo.ctddev.gromov.vkphotosx.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_detail.*
import ru.ifmo.ctddev.gromov.vkphotosx.R
import ru.ifmo.ctddev.gromov.vkphotosx.databinding.ActivityDetailBinding
import ru.ifmo.ctddev.gromov.vkphotosx.viewmodel.DetailViewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var viewmodel: DetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        viewmodel = ViewModelProviders.of(this, DetailViewModel.ModelFactory(intent.getParcelableExtra("POST")))
            .get(DetailViewModel::class.java)
        DataBindingUtil.setContentView<ActivityDetailBinding>(this, R.layout.activity_detail).apply { vm = viewmodel }
        viewmodel.loadImage(image)
    }
}