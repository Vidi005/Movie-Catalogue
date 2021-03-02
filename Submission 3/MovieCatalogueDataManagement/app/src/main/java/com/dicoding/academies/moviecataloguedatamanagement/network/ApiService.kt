package com.dicoding.academies.moviecataloguedatamanagement.network

import com.dicoding.academies.moviecataloguedatamanagement.data.source.remote.response.DetailMovieResponse
import com.dicoding.academies.moviecataloguedatamanagement.data.source.remote.response.DetailTVShowResponse
import com.dicoding.academies.moviecataloguedatamanagement.data.source.remote.response.MovieResponse
import com.dicoding.academies.moviecataloguedatamanagement.data.source.remote.response.TVShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("discover/{discoverPath}")
    fun getMovies(
        @Path("discoverPath") discoverPath: String,
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("sort_by") sort_by: String,
        @Query("include_adult") include_adult: Boolean
    ): Call<MovieResponse>

    @GET("discover/{discoverPath}")
    fun getTvShows(
        @Path("discoverPath") discoverPath: String,
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("sort_by") sort_by: String
    ): Call<TVShowResponse>

    @GET("movie/{movieId}")
    fun getDetailMovie(
        @Path("movieId") movieId: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): Call<DetailMovieResponse>

    @GET("tv/{tvId}")
    fun getDetailTVShow(
        @Path("tvId") tvId: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): Call<DetailTVShowResponse>
}