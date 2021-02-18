package com.dicoding.academies.moviecataloguetmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.academies.moviecataloguetmdb.data.MovieCatalogueRepository
import com.dicoding.academies.moviecataloguetmdb.data.source.local.entity.DetailMovieEntity

class DetailMovieViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) :
    ViewModel() {

    private var movieId: Int? = null

    fun setSelectedMovie(movieId: Int) {
        this.movieId = movieId
    }

    fun getDetailMovie(): LiveData<DetailMovieEntity> =
        movieId?.let { movieCatalogueRepository.getDetailMovie(it) } as LiveData<DetailMovieEntity>
}