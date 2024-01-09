package com.example.tmdbapi.data.repository

import androidx.lifecycle.LiveData
import com.example.tmdbapi.data.local.Favorite
import com.example.tmdbapi.data.local.FavoritesDatabase
import javax.inject.Inject

class FavoritesRepository @Inject constructor(private val database: FavoritesDatabase) {

    fun getFavorites(): LiveData<List<Favorite>> {
        return database.dao.getAllFavorites()
    }

    suspend fun insertFavorite(favorite: Favorite) {
        database.dao.insertFavorite(favorite)
    }

    suspend fun deleteFavorite(favorite: Favorite) {
        database.dao.deleteFavorite(favorite)
    }

    suspend fun deleteAllFavorites() {
        database.dao.deleteAllFavorites()
    }

    fun isFavorite(mediaId: Int): LiveData<Boolean>{
        return database.dao.isFavorite(mediaId)
    }
}