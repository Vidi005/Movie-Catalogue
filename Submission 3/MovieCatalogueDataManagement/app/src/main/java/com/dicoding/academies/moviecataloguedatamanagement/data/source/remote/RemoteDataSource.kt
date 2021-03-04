package com.dicoding.academies.moviecataloguedatamanagement.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.academies.moviecataloguedatamanagement.BuildConfig
import com.dicoding.academies.moviecataloguedatamanagement.data.source.remote.response.*
import com.dicoding.academies.moviecataloguedatamanagement.network.ApiConfig.getApiService
import com.dicoding.academies.moviecataloguedatamanagement.utils.EspressoIdlingResource.decrement
import com.dicoding.academies.moviecataloguedatamanagement.utils.EspressoIdlingResource.increment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource = instance ?: synchronized(this) {
            instance ?: RemoteDataSource()
        }

        private const val MOVIE_PATH = "movie"
        private const val TV_SHOW_PATH = "tv"
        private const val API_KEY = BuildConfig.TMDB_API_KEY
        private const val LANGUAGE_QUERY = "en-US"
        private const val SORT_QUERY = "popularity.desc"
        private val TAG = RemoteDataSource::class.java.simpleName
    }

    fun getMovies(): LiveData<ApiResponse<List<MoviesItemResponse>>> {
        increment()
        val resultMovies = MutableLiveData<ApiResponse<List<MoviesItemResponse>>>()
        val client = getApiService()
            .getMovies(MOVIE_PATH, API_KEY, LANGUAGE_QUERY, SORT_QUERY, false)
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            )
            {
                if (response.isSuccessful)
                    resultMovies.postValue(ApiResponse.success(response.body()
                        ?.results as List<MoviesItemResponse>))
                else {
                    ApiResponse.empty(response.message(), response.body())
                    Log.e(TAG, response.message())
                }
                decrement()
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                ApiResponse.error("${t.message}", null)
                Log.e(TAG, "${t.message}")
                decrement()
            }
        })
        return resultMovies
    }

    fun getTvShows(): LiveData<ApiResponse<List<TVShowsItemResponse>>> {
        increment()
        val resultTvShows = MutableLiveData<ApiResponse<List<TVShowsItemResponse>>>()
        val client = getApiService()
            .getTvShows(TV_SHOW_PATH, API_KEY, LANGUAGE_QUERY, SORT_QUERY)
        client.enqueue(object : Callback<TVShowResponse> {
            override fun onResponse(
                call: Call<TVShowResponse>,
                response: Response<TVShowResponse>
            )
            {
                if (response.isSuccessful)
                    resultTvShows.postValue(response.body()?.let { ApiResponse.success(it.results) })
                else {
                    ApiResponse.empty(response.message(), response.errorBody())
                    Log.e(TAG, response.message())
                }
                decrement()
            }

            override fun onFailure(call: Call<TVShowResponse>, t: Throwable) {
                ApiResponse.error(t.message.toString(), null)
                Log.d(TAG, t.message.toString())
                decrement()
            }
        })
        return resultTvShows
    }

    fun getDetailMovie(movieId: Int): MutableLiveData<ApiResponse<DetailMovieResponse>> {
        increment()
        val resultDetailMovie = MutableLiveData<ApiResponse<DetailMovieResponse>>()
        val client = getApiService().getDetailMovie(movieId, API_KEY, LANGUAGE_QUERY)
        client.enqueue(object : Callback<DetailMovieResponse> {
            override fun onResponse(
                call: Call<DetailMovieResponse>,
                response: Response<DetailMovieResponse>
            )
            {
                if (response.isSuccessful)
                    resultDetailMovie
                        .postValue(ApiResponse.success(response.body() as DetailMovieResponse))
                else {
                    ApiResponse.empty(response.message(), response.errorBody())
                    Log.e(TAG, response.message())
                }
                decrement()
            }

            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                ApiResponse.error("${t.message}", null)
                Log.e(TAG, t.message.toString())
                decrement()
            }
        })
        return resultDetailMovie
    }

    fun getDetailTVShow(tvId: Int): MutableLiveData<ApiResponse<DetailTVShowResponse>> {
        increment()
        val resultDetailTvShow = MutableLiveData<ApiResponse<DetailTVShowResponse>>()
        val client = getApiService().getDetailTVShow(tvId, API_KEY, LANGUAGE_QUERY)
        client.enqueue(object : Callback<DetailTVShowResponse> {
            override fun onResponse(
                call: Call<DetailTVShowResponse>,
                response: Response<DetailTVShowResponse>
            )
            {
                if (response.isSuccessful)
                    resultDetailTvShow
                        .postValue(ApiResponse.success(response.body() as DetailTVShowResponse))
                else {
                    ApiResponse.empty(response.message(), response.errorBody())
                    Log.e(TAG, response.message())
                }
                decrement()
            }

            override fun onFailure(call: Call<DetailTVShowResponse>, t: Throwable) {
                ApiResponse.error(t.message.toString(), null)
                Log.e(TAG, "${t.message}")
                decrement()
            }
        })
        return resultDetailTvShow
    }
}
