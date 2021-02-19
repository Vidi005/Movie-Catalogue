package com.dicoding.academies.moviecataloguetmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.academies.moviecataloguetmdb.data.MovieCatalogueDataSource
import com.dicoding.academies.moviecataloguetmdb.data.source.local.entity.DetailTVShowEntity

class DetailTVShowViewModel(private val movieCatalogueDataSource: MovieCatalogueDataSource) :
    ViewModel() {

    private var tvId: Int? = 0

    fun setSelectedTVShow(tvId: Int) {
        this.tvId = tvId
    }

    fun getDetailTVShow(): LiveData<DetailTVShowEntity> =
        tvId?.let { movieCatalogueDataSource.getDetailTVShow(it) } as LiveData<DetailTVShowEntity>
}
