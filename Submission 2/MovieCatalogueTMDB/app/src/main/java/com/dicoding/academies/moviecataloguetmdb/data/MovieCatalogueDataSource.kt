package com.dicoding.academies.moviecataloguetmdb.data

import androidx.lifecycle.LiveData
import com.dicoding.academies.moviecataloguetmdb.data.source.local.entity.DetailMovieEntity
import com.dicoding.academies.moviecataloguetmdb.data.source.local.entity.DetailTVShowEntity
import com.dicoding.academies.moviecataloguetmdb.data.source.local.entity.MovieEntity
import com.dicoding.academies.moviecataloguetmdb.data.source.local.entity.TVShowEntity

interface MovieCatalogueDataSource {

    fun getMovies(): LiveData<List<MovieEntity>>

    fun getTvShows(): LiveData<List<TVShowEntity>>

    fun getDetailMovie(movieId: Int): LiveData<DetailMovieEntity>

    fun getDetailTVShow(tvId: Int): LiveData<DetailTVShowEntity>

}
