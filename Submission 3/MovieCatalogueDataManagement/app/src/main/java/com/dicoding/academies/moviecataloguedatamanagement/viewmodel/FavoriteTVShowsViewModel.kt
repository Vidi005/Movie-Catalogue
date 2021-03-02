package com.dicoding.academies.moviecataloguedatamanagement.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.academies.moviecataloguedatamanagement.data.MovieCatalogueRepository
import com.dicoding.academies.moviecataloguedatamanagement.data.source.local.entity.TVShowEntity

class FavoriteTVShowsViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) :
    ViewModel() {

    fun getFavoriteTVShows(sort: String): LiveData<PagedList<TVShowEntity>> =
        movieCatalogueRepository.getFavoredTVShows(sort)
}