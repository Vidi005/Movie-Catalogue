package com.dicoding.picodiploma.moviecatalogue.viewmodel

import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.moviecatalogue.data.TVShowEntity
import com.dicoding.picodiploma.moviecatalogue.utils.DataDummy

class DetailTvShowViewModel : ViewModel() {

    private lateinit var tvShowName: String

    fun setSelectedTvShow(tvShowName: String) {
        this.tvShowName = tvShowName
    }

    fun getDetailTvShow(): TVShowEntity {
        lateinit var tvShow: TVShowEntity
        val tvShowEntities = DataDummy.generateDummyTVShowsItem()
        for (tvShowEntity in tvShowEntities)
            if (tvShowEntity.tvShowName == tvShowName) tvShow = tvShowEntity
        return tvShow
    }
}