package com.example.tmdbapi.di

import android.app.Application
import androidx.room.Room
import com.example.tmdbapi.data.local.FavoritesDatabase
import com.example.tmdbapi.data.remote.ServiceApi
import com.example.tmdbapi.data.repository.GenresRepository
import com.example.tmdbapi.data.repository.MovieDetailsRepository
import com.example.tmdbapi.data.repository.MoviesRepository
import com.example.tmdbapi.data.repository.SearchRepository
import com.example.tmdbapi.util.Constants.BASE_URL
import com.example.tmdbapi.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .callTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)

        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun providesTMDBApi(okHttpClient: OkHttpClient): ServiceApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ServiceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFavoritesDatabase(application: Application): FavoritesDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            FavoritesDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideMoviesRepository(api: ServiceApi) = MoviesRepository(api)

    @Provides
    @Singleton
    fun provideMoviesDetailsRepository(api: ServiceApi) = MovieDetailsRepository(api)

    @Provides
    @Singleton
    fun provideGenresRepository(api: ServiceApi) = GenresRepository(api)

    @Provides
    @Singleton
    fun provideSearchRepository(api: ServiceApi) = SearchRepository(api)
}