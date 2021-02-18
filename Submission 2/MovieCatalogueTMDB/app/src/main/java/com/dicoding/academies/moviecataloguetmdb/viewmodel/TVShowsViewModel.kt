package com.dicoding.academies.moviecataloguetmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.academies.moviecataloguetmdb.data.MovieCatalogueRepository
import com.dicoding.academies.moviecataloguetmdb.data.source.local.entity.TVShowEntity

class TVShowsViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) :
    ViewModel() {

    fun getTvShows(): LiveData<List<TVShowEntity>> = movieCatalogueRepository.getTvShows()
}