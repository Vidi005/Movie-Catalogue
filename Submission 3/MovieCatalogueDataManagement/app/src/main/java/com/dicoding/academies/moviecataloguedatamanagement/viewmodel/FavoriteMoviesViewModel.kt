package com.dicoding.academies.moviecataloguedatamanagement.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.academies.moviecataloguedatamanagement.data.MovieCatalogueRepository
import com.dicoding.academies.moviecataloguedatamanagement.data.source.local.entity.MovieEntity

class FavoriteMoviesViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) :
    ViewModel() {

    fun getFavoriteMovies(sort: String): LiveData<PagedList<MovieEntity>> =
        movieCatalogueRepository.getFavoredMovies(sort)
}