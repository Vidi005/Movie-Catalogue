package com.dicoding.picodiploma.moviecatalogue.viewmodel

import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.moviecatalogue.data.MovieEntity
import com.dicoding.picodiploma.moviecatalogue.utils.DataDummy.generateDummyMoviesItem

class MoviesViewModel : ViewModel() {

    fun getMovies(): ArrayList<MovieEntity> = generateDummyMoviesItem()
}