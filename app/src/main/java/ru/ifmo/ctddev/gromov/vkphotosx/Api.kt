package ru.ifmo.ctddev.gromov.vkphotosx

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("method/photos.search")
    fun getAsync(
        @Query("q") q: String = "landscape Fjord",
        @Query("v") v: String = "5.92",
        @Query("count") count: String = "25",
        @Query("access_token") access_token: String = "7b4070287b4070287b4070289c7b26c24477b407b407028209043d6e20082bb38b48bd7"
    ): Deferred<Data>
}