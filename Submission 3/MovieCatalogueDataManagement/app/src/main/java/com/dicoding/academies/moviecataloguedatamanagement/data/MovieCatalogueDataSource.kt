package com.dicoding.academies.moviecataloguedatamanagement.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dicoding.academies.moviecataloguedatamanagement.data.source.local.entity.*
import com.dicoding.academies.moviecataloguedatamanagement.vo.Resource

interface MovieCatalogueDataSource {

    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getTvShows(): LiveData<Resource<PagedList<TVShowEntity>>>

    fun getDetailMovie(movieId: Int): LiveData<Resource<MovieEntity>>

    fun getDetailTVShow(tvId: Int): LiveData<Resource<TVShowEntity>>

    fun setFavoriteMovie(movie: MovieEntity, state: Boolean)

    fun setFavoriteTVShow(tvShow: TVShowEntity, state: Boolean)

    fun getFavoredMovies(sort: String): LiveData<PagedList<MovieEntity>>

    fun getFavoredTVShows(sort: String): LiveData<PagedList<TVShowEntity>>
}