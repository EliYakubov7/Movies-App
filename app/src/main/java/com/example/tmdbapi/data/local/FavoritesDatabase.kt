package com.example.tmdbapi.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Favorite::class], version = 1)
abstract class FavoritesDatabase : RoomDatabase() {
    abstract val dao: FavoritesDao
}