package ru.ifmo.ctddev.gromov.vkphotosx.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.ifmo.ctddev.gromov.vkphotosx.model.App
import ru.ifmo.ctddev.gromov.vkphotosx.model.room.Post

class MainViewModel : ViewModel() {
    val data: LiveData<List<Post>> = App.getData()
    var request: String = "landscape fjord"
    fun reloadList(): Unit = App.reloadData(request)
}