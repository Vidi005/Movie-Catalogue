package com.dicoding.academies.moviecataloguedatamanagement.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.academies.moviecataloguedatamanagement.data.MovieCatalogueRepository
import com.dicoding.academies.moviecataloguedatamanagement.data.source.local.entity.MovieEntity
import com.dicoding.academies.moviecataloguedatamanagement.vo.Resource

class MoviesViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) :
    ViewModel() {

    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> =
        movieCatalogueRepository.getMovies()
}