package ru.ifmo.ctddev.gromov.vkphotosx.model

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
    val api: Api = retrofit()
        .create(Api::class.java)
    private fun retrofit(): Retrofit = Retrofit.Builder().baseUrl("https://api.vk.com/")
        .addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(CoroutineCallAdapterFactory()).build()
}