package com.example.tmdbapi.screens.details

import androidx.lifecycle.ViewModel
import com.example.tmdbapi.data.remote.responses.MovieDetailsResponse
import com.example.tmdbapi.data.repository.MovieDetailsRepository
import com.example.tmdbapi.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val repository: MovieDetailsRepository
) : ViewModel() {

    suspend fun getMovieDetails(movieId: Int): Resource<MovieDetailsResponse> {
        return repository.getMoviesDetails(movieId)
    }
}