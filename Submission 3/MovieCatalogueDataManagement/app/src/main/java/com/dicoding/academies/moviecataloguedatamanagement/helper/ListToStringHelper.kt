package com.dicoding.academies.moviecataloguedatamanagement.helper

import com.dicoding.academies.moviecataloguedatamanagement.data.source.remote.response.DetailMovieResponse
import com.dicoding.academies.moviecataloguedatamanagement.data.source.remote.response.DetailTVShowResponse

object ListToStringHelper {

    fun movieGenreListToStringConverter(detailMovieResponse: DetailMovieResponse): String {
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
        return genresString.toString()
    }

    fun movieProductionCompanyListToStringConverter(detailMovieResponse: DetailMovieResponse):
        String
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
        return productionCompaniesString.toString()
    }

    fun tvShowGenreListToStringConverter(detailTVShowResponse: DetailTVShowResponse): String {
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
        return genresString.toString()
    }

    fun tvShowProductionCompanyListToStringConverter(detailTVShowResponse: DetailTVShowResponse):
        String {
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
        return productionCompaniesString.toString()
    }
}