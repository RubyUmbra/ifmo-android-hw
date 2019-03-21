package ru.ifmo.ctddev.gromov.vkphotosx.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.ifmo.ctddev.gromov.vkphotosx.Post

@Database(entities = [Post::class], version = 1)
abstract class AppDB : RoomDatabase() {
    abstract fun postDAO(): PostDAO
}