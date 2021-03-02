package com.dicoding.academies.moviecataloguedatamanagement.ui.favorite.callback

import com.dicoding.academies.moviecataloguedatamanagement.data.source.local.entity.TVShowEntity

interface OnFavoriteTVShowItemClickCallback {
    fun onItemClicked(data: TVShowEntity)
    fun onItemShared(data: TVShowEntity)
}
