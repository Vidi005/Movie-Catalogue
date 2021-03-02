package com.dicoding.academies.moviecataloguedatamanagement.di

import android.content.Context
import com.dicoding.academies.moviecataloguedatamanagement.data.MovieCatalogueRepository
import com.dicoding.academies.moviecataloguedatamanagement.data.source.local.LocalDataSource
import com.dicoding.academies.moviecataloguedatamanagement.data.source.local.room.MovieCatalogueDatabase
import com.dicoding.academies.moviecataloguedatamanagement.data.source.remote.RemoteDataSource
import com.dicoding.academies.moviecataloguedatamanagement.utils.AppExecutors

object Injection {

    fun provideRepository(context: Context): MovieCatalogueRepository {
        val database = MovieCatalogueDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.movieCatalogueDao())
        val appExecutors = AppExecutors()
        return MovieCatalogueRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}