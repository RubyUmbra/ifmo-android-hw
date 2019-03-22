package ru.ifmo.ctddev.gromov.vkphotosx.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.ifmo.ctddev.gromov.vkphotosx.model.App
import ru.ifmo.ctddev.gromov.vkphotosx.model.room.Post

class MainViewModel : ViewModel() {
    val data: LiveData<List<Post>> = App.db.postDAO().all()
    fun reloadList(request: String): Unit = App.instance.reloadList(request)
}