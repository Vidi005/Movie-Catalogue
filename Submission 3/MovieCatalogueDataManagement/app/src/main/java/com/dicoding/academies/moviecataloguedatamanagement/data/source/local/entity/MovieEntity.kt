package com.dicoding.academies.moviecataloguedatamanagement.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_entity")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo
    val id: Int?,

    @NonNull
    @ColumnInfo
    val posterPath: String?,

    @NonNull
    @ColumnInfo
    val title: String?,

    @NonNull
    @ColumnInfo
    val releaseDate: String?,

    @NonNull
    @ColumnInfo
    val genres: String?,

    @NonNull
    @ColumnInfo
    val popularity: Double?,

    @NonNull
    @ColumnInfo
    val score: Int?,

    @NonNull
    @ColumnInfo
    val tagLine: String?,

    @NonNull
    @ColumnInfo
    val overview: String?,

    @NonNull
    @ColumnInfo
    val productionCompanies: String?,

    @ColumnInfo
    var isFavorite: Boolean = false
)
