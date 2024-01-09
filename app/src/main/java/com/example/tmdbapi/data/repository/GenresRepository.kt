package com.example.tmdbapi.data.repository

import com.example.tmdbapi.data.remote.ServiceApi
import com.example.tmdbapi.data.remote.responses.GenresResponse
import com.example.tmdbapi.util.Resource
import timber.log.Timber
import javax.inject.Inject

class GenresRepository @Inject constructor(private val api: ServiceApi) {

    suspend fun getMoviesGenres(): Resource<GenresResponse> {
        val response = try {
            api.getMovieGenres()
        } catch (e: Exception) {
            return Resource.Error("Unknown error occurred")
        }
        Timber.d("Movies genres: $response")
        return Resource.Success(response)
    }

}