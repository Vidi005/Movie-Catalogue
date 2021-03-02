package com.dicoding.academies.moviecataloguedatamanagement.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_show_entity")
data class TVShowEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo
    val id: Int?,

    @NonNull
    @ColumnInfo
    val posterPath: String?,

    @NonNull
    @ColumnInfo
    val name: String?,

    @NonNull
    @ColumnInfo
    val firstAirDate: String?,

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
