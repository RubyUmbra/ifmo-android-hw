package ru.ifmo.ctddev.gromov.vkphotosx.model

import android.app.Application
import androidx.room.Room
import kotlinx.coroutines.*
import ru.ifmo.ctddev.gromov.vkphotosx.model.room.AppDB
import ru.ifmo.ctddev.gromov.vkphotosx.model.room.Post

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        db = Room.databaseBuilder(this, AppDB::class.java, "database").build()
        reloadList()
    }

    fun reloadList(request: String = "landscape Fjord") {
        GlobalScope.launch {
            withContext(Dispatchers.Default) { db.postDAO().clean() }
            withContext(Dispatchers.Default) {
                db.postDAO().insertAll(
                    ApiFactory.api.getAsync(q = request).await()
                    .response.items.map {
                    Post(
                        text = it.text,
                        url = it.sizes.last().url
                    )
                })
            }
        }
    }

    companion object {
        lateinit var instance: App
        lateinit var db: AppDB
    }
}