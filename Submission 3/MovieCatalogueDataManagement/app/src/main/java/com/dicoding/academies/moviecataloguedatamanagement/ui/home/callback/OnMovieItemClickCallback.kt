package com.dicoding.academies.moviecataloguedatamanagement.ui.home.callback

import com.dicoding.academies.moviecataloguedatamanagement.data.source.local.entity.MovieEntity

interface OnMovieItemClickCallback {
    fun onItemClicked(data: MovieEntity)
    fun onItemShared(data: MovieEntity)
}
