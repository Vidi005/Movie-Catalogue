package com.dicoding.academies.moviecataloguetmdb.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.academies.moviecataloguetmdb.data.source.local.entity.DetailMovieEntity
import com.dicoding.academies.moviecataloguetmdb.data.source.local.entity.DetailTVShowEntity
import com.dicoding.academies.moviecataloguetmdb.data.source.local.entity.MovieEntity
import com.dicoding.academies.moviecataloguetmdb.data.source.local.entity.TVShowEntity
import com.dicoding.academies.moviecataloguetmdb.data.source.remote.RemoteDataSource
import com.dicoding.academies.moviecataloguetmdb.data.source.remote.response.DetailMovieResponse
import com.dicoding.academies.moviecataloguetmdb.data.source.remote.response.DetailTVShowResponse
import com.dicoding.academies.moviecataloguetmdb.data.source.remote.response.MoviesItemResponse
import com.dicoding.academies.moviecataloguetmdb.data.source.remote.response.TVShowsItemResponse
import com.dicoding.academies.moviecataloguetmdb.helper.ListToStringHelper.movieGenreListToStringConverter
import com.dicoding.academies.moviecataloguetmdb.helper.ListToStringHelper.movieProductionCompanyListToStringConverter
import com.dicoding.academies.moviecataloguetmdb.helper.ListToStringHelper.tvShowGenreListToStringConverter
import com.dicoding.academies.moviecataloguetmdb.helper.ListToStringHelper.tvShowProductionCompanyListToStringConverter
import kotlin.math.roundToInt

class MovieCatalogueRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    MovieCatalogueDataSource {

    companion object {
        @Volatile
        private var instance: MovieCatalogueRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): MovieCatalogueRepository =
                instance ?: synchronized(this) {
                    instance ?: MovieCatalogueRepository(remoteDataSource)
                }
    }

    override fun getMovies(): LiveData<List<MovieEntity>> {
        val movieResults = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onMoviesReceived(movieResponses: List<MoviesItemResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in movieResponses) {
                    val movie = with(response) {
                        MovieEntity(id, posterPath, title, overview, releaseDate)
                    }
                    movieList.add(movie)
                }
                Log.d("MovieResponse", "$movieResponses")
                movieResults.postValue(movieList)
                Log.d("MovieCatalogueRepo", "$movieList")
            }
        })
        return movieResults
    }

    override fun getTvShows(): LiveData<List<TVShowEntity>> {
        val tvShowResults = MutableLiveData<List<TVShowEntity>>()
        remoteDataSource.getTvShows(object : RemoteDataSource.LoadTvShowsCallback {
            override fun onTvShowsReceived(tvShowResponses: List<TVShowsItemResponse>) {
                val tvShowList = ArrayList<TVShowEntity>()
                tvShowResponses.forEach {
                    val tvShow = with(it) {
                        TVShowEntity(id, posterPath, name, overview, firstAirDate)
                    }
                    tvShowList.add(tvShow)
                }
                tvShowResults.postValue(tvShowList)
            }
        })
        return tvShowResults
    }

    override fun getDetailMovie(movieId: Int): LiveData<DetailMovieEntity> {
        val detailMovieResult = MutableLiveData<DetailMovieEntity>()
        remoteDataSource.getDetailMovie(movieId, object : RemoteDataSource.LoadDetailMovieCallback {
            override fun onDetailMovieReceived(detailMovieResponse: DetailMovieResponse) {
                val detailMovieEntity = with(detailMovieResponse) {
                    DetailMovieEntity(id,
                        posterPath,
                        title,
                        releaseDate,
                        movieGenreListToStringConverter(detailMovieResponse).toString(),
                        popularity,
                        (voteAverage * 10).roundToInt(),
                        tagline,
                        overview,
                        movieProductionCompanyListToStringConverter(detailMovieResponse).toString())
                }
                detailMovieResult.postValue(detailMovieEntity)
            }
        })
        return detailMovieResult
    }

    override fun getDetailTVShow(tvId: Int): LiveData<DetailTVShowEntity> {
        val detailTvShowResult = MutableLiveData<DetailTVShowEntity>()
        remoteDataSource.getDetailTVShow(tvId, object : RemoteDataSource.LoadDetailTvShowCallback {
            override fun onDetailTVShowReceived(detailTVShowResponse: DetailTVShowResponse) {
                val detailTvShowEntity = with(detailTVShowResponse) {
                    DetailTVShowEntity(id,
                        posterPath,
                        name,
                        firstAirDate,
                        tvShowGenreListToStringConverter(detailTVShowResponse).toString(),
                        popularity,
                        (voteAverage * 10).roundToInt(),
                        tagline,
                        overview,
                        tvShowProductionCompanyListToStringConverter(detailTVShowResponse).toString()
                    )
                }
                detailTvShowResult.postValue(detailTvShowEntity)
            }
        })
        return detailTvShowResult
    }
}