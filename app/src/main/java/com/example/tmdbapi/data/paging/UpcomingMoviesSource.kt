package com.example.tmdbapi.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.tmdbapi.data.remote.ServiceApi
import com.example.tmdbapi.model.Movie
import retrofit2.HttpException
import java.io.IOException

class UpcomingMoviesSource(private val api: ServiceApi) :
    PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPage = params.key ?: 1
            val upcomingComingMovies = api.getUpcomingMovies(nextPage)
            LoadResult.Page(
                data = upcomingComingMovies.searches,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (upcomingComingMovies.searches.isEmpty()) null else upcomingComingMovies.page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}