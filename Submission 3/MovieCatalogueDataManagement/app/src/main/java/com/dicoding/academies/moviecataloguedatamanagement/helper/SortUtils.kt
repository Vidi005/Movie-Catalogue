package com.dicoding.academies.moviecataloguedatamanagement.helper

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {

    const val ASCENDING = "Ascending"
    const val DESCENDING = "Descending"
    const val NEWEST = "Newest"
    const val OLDEST = "Oldest"

    fun getMoviesSortedQuery(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM movie_entity WHERE isFavorite = 1 ")
        when (filter) {
            ASCENDING -> simpleQuery.append("ORDER BY title ASC")
            DESCENDING -> simpleQuery.append("ORDER BY title DESC")
            NEWEST -> simpleQuery.append("ORDER BY releaseDate DESC")
            OLDEST -> simpleQuery.append("ORDER BY releaseDate ASC")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

    fun getTvShowsSortedQuery(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM tv_show_entity WHERE isFavorite = 1 ")
        when (filter) {
            ASCENDING -> simpleQuery.append("ORDER BY name ASC")
            DESCENDING -> simpleQuery.append("ORDER BY name DESC")
            NEWEST -> simpleQuery.append("ORDER BY firstAirDate DESC")
            OLDEST -> simpleQuery.append("ORDER BY firstAirDate ASC")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}