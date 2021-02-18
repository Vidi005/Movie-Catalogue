package com.dicoding.academies.moviecataloguetmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.academies.moviecataloguetmdb.data.MovieCatalogueRepository
import com.dicoding.academies.moviecataloguetmdb.data.source.local.entity.MovieEntity

class MoviesViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) :
    ViewModel() {

    fun getMovies(): LiveData<List<MovieEntity>> = movieCatalogueRepository.getMovies()
}