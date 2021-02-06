package com.dicoding.picodiploma.moviecatalogue.viewmodel

import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.moviecatalogue.data.TVShowEntity
import com.dicoding.picodiploma.moviecatalogue.utils.DataDummy.generateDummyTVShowsItem

class TvShowsViewModel : ViewModel() {

    fun getTVShows(): ArrayList<TVShowEntity> = generateDummyTVShowsItem()
}