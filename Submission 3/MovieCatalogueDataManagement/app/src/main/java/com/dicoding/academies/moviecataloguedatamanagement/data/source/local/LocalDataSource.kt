package com.dicoding.academies.moviecataloguedatamanagement.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.dicoding.academies.moviecataloguedatamanagement.data.source.local.entity.*
import com.dicoding.academies.moviecataloguedatamanagement.data.source.local.room.MovieCatalogueDao
import com.dicoding.academies.moviecataloguedatamanagement.helper.SortUtils.getMoviesSortedQuery
import com.dicoding.academies.moviecataloguedatamanagement.helper.SortUtils.getTvShowsSortedQuery

class LocalDataSource private constructor(private val mMovieCatalogueDao: MovieCatalogueDao) {

    companion object {
        private val INSTANCE: LocalDataSource? = null

        fun getInstance(movieCatalogueDao: MovieCatalogueDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieCatalogueDao)
    }

    fun getMovies(): DataSource.Factory<Int, MovieEntity> = mMovieCatalogueDao.getMovies()

    fun getFavoredMovies(sort: String): DataSource.Factory<Int, MovieEntity> {
        val query = getMoviesSortedQuery(sort)
        return mMovieCatalogueDao.getFavoriteMovies(query)
    }

    fun getDetailMovie(movieId: Int): LiveData<MovieEntity> =
        mMovieCatalogueDao.getDetailMovieById(movieId)

    fun insertMovies(movies: List<MovieEntity>) = mMovieCatalogueDao.insertMovies(movies)

    fun insertDetailMovie(detailMovie: MovieEntity) =
        mMovieCatalogueDao.insertDetailMovie(detailMovie)

    fun setMovieFavorite(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        mMovieCatalogueDao.updateMovie(movie)
    }

    fun getTVShows(): DataSource.Factory<Int, TVShowEntity> = mMovieCatalogueDao.getTVShows()

    fun getFavoredTVShows(sort: String): DataSource.Factory<Int, TVShowEntity> =
        mMovieCatalogueDao.getFavoriteTVShows(getTvShowsSortedQuery(sort))

    fun getDetailTVShow(tvId: Int): LiveData<TVShowEntity> =
        mMovieCatalogueDao.getDetailTVShowById(tvId)

    fun insertTVShows(tvShow: List<TVShowEntity>) = mMovieCatalogueDao.insertTVShows(tvShow)

    fun insertDetailTVShow(detailTVShow: TVShowEntity) =
        mMovieCatalogueDao.insertDetailTVShow(detailTVShow)

    fun setTVShowFavorite(tvShow: TVShowEntity, newState: Boolean) {
        tvShow.isFavorite = newState
        mMovieCatalogueDao.updateTVShow(tvShow)
    }
}