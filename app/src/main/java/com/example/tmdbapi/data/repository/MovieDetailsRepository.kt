package com.example.tmdbapi.data.repository

import com.example.tmdbapi.data.remote.ServiceApi
import com.example.tmdbapi.data.remote.responses.MovieDetailsResponse
import com.example.tmdbapi.util.Resource
import timber.log.Timber
import javax.inject.Inject

class MovieDetailsRepository @Inject constructor(private val api: ServiceApi) {

    suspend fun getMoviesDetails(movieId: Int): Resource<MovieDetailsResponse> {
        val response = try {
            api.getMovieDetails(movieId)
        } catch (e: Exception) {
            return Resource.Error("Unknown error occurred")
        }
        Timber.d("Movie details: $response")
        return Resource.Success(response)
    }

}