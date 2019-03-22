package ru.ifmo.ctddev.gromov.vkphotosx.model

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.ifmo.ctddev.gromov.vkphotosx.model.room.AppDB
import ru.ifmo.ctddev.gromov.vkphotosx.model.room.Post

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        db = Room.databaseBuilder(this, AppDB::class.java, "database").build()
        api = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory()).build().create(VkSearchApi::class.java)
        reloadData()
    }

    companion object {
        private const val BASE_URL: String = "https://api.vk.com/"
        private lateinit var instance: App
        private lateinit var db: AppDB
        private lateinit var api: VkSearchApi

        private suspend fun loadDataFromWeb(request: String) =
            api.getAsync(q = request).await().response.items.map { Post(text = it.text, url = it.sizes.last().url) }

        fun getData(): LiveData<List<Post>> = db.postDAO().all()

        fun updateData(post: Post) {
            GlobalScope.launch { db.postDAO().update(post) }
        }

        fun reloadData(request: String = "landscape fjord") {
            GlobalScope.launch {
                db.postDAO().clean()
                db.postDAO().insertAll(loadDataFromWeb(request))
            }
        }
    }
}