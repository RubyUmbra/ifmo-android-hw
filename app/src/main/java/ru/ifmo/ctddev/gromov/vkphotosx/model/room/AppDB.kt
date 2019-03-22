package ru.ifmo.ctddev.gromov.vkphotosx.model.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Post::class], version = 1)
abstract class AppDB : RoomDatabase() {
    abstract fun postDAO(): PostDAO
}