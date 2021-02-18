package com.dicoding.academies.moviecataloguetmdb.data.source.local.entity

data class DetailMovieEntity(
    val id: Int?,
    val posterPath: String?,
    val title: String?,
    val releaseDate: String?,
    val genres: String?,
    val popularity: Double?,
    val score: Int?,
    val tagLine: String?,
    val overview: String?,
    val productionCompanies: String?
)
