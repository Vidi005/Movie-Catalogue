package com.dicoding.academies.moviecataloguetmdb.di

import com.dicoding.academies.moviecataloguetmdb.data.MovieCatalogueRepository
import com.dicoding.academies.moviecataloguetmdb.data.source.remote.RemoteDataSource

object Injection {

    fun provideRepository(): MovieCatalogueRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return MovieCatalogueRepository.getInstance(remoteDataSource)
    }
}