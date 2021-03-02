package com.dicoding.academies.moviecataloguedatamanagement.ui.favorite.callback

import com.dicoding.academies.moviecataloguedatamanagement.data.source.local.entity.MovieEntity

interface OnFavoriteMovieItemClickCallback {
    fun onItemClicked(data: MovieEntity)
    fun onItemShared(data: MovieEntity)
}
