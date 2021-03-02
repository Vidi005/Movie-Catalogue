package com.dicoding.academies.moviecataloguedatamanagement.ui.home.callback

import com.dicoding.academies.moviecataloguedatamanagement.data.source.local.entity.TVShowEntity

interface OnTVShowItemClickCallback {
    fun onItemClicked(data: TVShowEntity)
    fun onItemShared(data: TVShowEntity)
}
