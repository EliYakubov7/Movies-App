package com.example.tmdbapi.data.remote.responses

import com.example.tmdbapi.model.Genre
import com.google.gson.annotations.SerializedName

data class GenresResponse(
    @SerializedName("genres")
    val genres: List<Genre>
)