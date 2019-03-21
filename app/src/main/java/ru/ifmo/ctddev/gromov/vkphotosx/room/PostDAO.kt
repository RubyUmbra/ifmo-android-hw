package ru.ifmo.ctddev.gromov.vkphotosx.room

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.ifmo.ctddev.gromov.vkphotosx.Post

@Dao
interface PostDAO {
    @Query("SELECT * FROM post")
    fun all(): LiveData<List<Post>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: Iterable<Post>)

    @Update
    suspend fun update(post: Post)

    @Query("DELETE FROM post WHERE isFave = 0")
    suspend fun clean()
}