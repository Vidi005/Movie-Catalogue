package com.dicoding.picodiploma.moviecatalogue.data

data class MovieEntity(
    val poster: Int?,
    val movieName: String?,
    val year: Int?,
    val release: String?,
    val genre: String?,
    val duration: String?,
    val score: Int?,
    val tagLine: String?,
    val overview: String?,
    val person: String?
)