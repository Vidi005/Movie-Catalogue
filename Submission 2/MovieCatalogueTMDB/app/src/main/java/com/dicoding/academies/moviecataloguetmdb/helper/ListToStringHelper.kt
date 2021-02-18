package com.dicoding.academies.moviecataloguetmdb.helper

import com.dicoding.academies.moviecataloguetmdb.data.source.remote.response.DetailMovieResponse
import com.dicoding.academies.moviecataloguetmdb.data.source.remote.response.DetailTVShowResponse

object ListToStringHelper {

    fun movieGenreListToStringConverter(detailMovieResponse: DetailMovieResponse): StringBuilder {
        val genresString = StringBuilder()
        var genreSeparator = false
        val genreMap = detailMovieResponse.genres.map {
            if (it.name.isNotBlank()) it.name else "-"
        }
        for (genre in genreMap) {
            if (genreSeparator) genresString.append(", ")
            genreSeparator = true
            genresString.append(genre)
        }
        return genresString
    }

    fun movieProductionCompanyListToStringConverter(detailMovieResponse: DetailMovieResponse):
        StringBuilder
    {
        val productionCompaniesString = StringBuilder()
        var productionCompanySeparator = false
        val productionCompanyMap = detailMovieResponse.productionCompanies.map {
            when {
                it.originCountry.isNotBlank() -> "${it.name} (${it.originCountry})"
                it.originCountry.isBlank() -> it.name
                else -> "-"
            }
        }
        productionCompanyMap.forEach {
            if (productionCompanySeparator) productionCompaniesString.append(", ")
            productionCompanySeparator = true
            productionCompaniesString.append(it)
        }
        return productionCompaniesString
    }

    fun tvShowGenreListToStringConverter(detailTVShowResponse: DetailTVShowResponse): StringBuilder {
        val genresString = StringBuilder()
        var genreSeparator = false
        val genreMap = detailTVShowResponse.genres.map {
            if (it.name.isNotBlank()) it.name else "-"
        }
        for (genre in genreMap) {
            if (genreSeparator) genresString.append(", ")
            genreSeparator = true
            genresString.append(genre)
        }
        return genresString
    }

    fun tvShowProductionCompanyListToStringConverter(detailTVShowResponse: DetailTVShowResponse):
        StringBuilder {
        val productionCompaniesString = StringBuilder()
        var productionCompanySeparator = false
        val productionCompanyMap = detailTVShowResponse.productionCompanies.map {
            when {
                it.originCountry.isNotBlank() -> "${it.name} (${it.originCountry})"
                it.originCountry.isBlank() -> it.name
                else -> "-"
            }
        }
        productionCompanyMap.forEach {
            if (productionCompanySeparator) productionCompaniesString.append(", ")
            productionCompanySeparator = true
            productionCompaniesString.append(it)
        }
        return productionCompaniesString
    }
}