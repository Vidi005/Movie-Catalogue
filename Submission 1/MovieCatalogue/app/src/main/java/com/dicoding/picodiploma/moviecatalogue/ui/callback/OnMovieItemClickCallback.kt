package com.dicoding.picodiploma.moviecatalogue.ui.callback

import com.dicoding.picodiploma.moviecatalogue.data.MovieEntity

interface OnMovieItemClickCallback {
    fun onItemClicked(data: MovieEntity)
    fun onItemShared(data: MovieEntity)
}
