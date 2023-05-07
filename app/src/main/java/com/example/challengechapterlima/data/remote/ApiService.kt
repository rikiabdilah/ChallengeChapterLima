package com.example.challengechapterlima.data.remote

import com.example.challengechapterlima.data.remote.model.DetailMovieResponse
import com.example.challengechapterlima.data.remote.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing")
    suspend fun getAllMoviesNowPlaying(
        @Query("api_key") apiKey: String = "4ab07d1052677ca3e6d60de1a63d00ec",
        @Query("page") page: Int = 1
    ): MovieResponse

    @GET("movie/{movie_id}?api_key=4ab07d1052677ca3e6d60de1a63d00ec")
    suspend fun getDetailMovie(
        @Path("movie_id") movieId: Int
    ): DetailMovieResponse

}