package com.dicoding.academies.moviecataloguedatamanagement.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.academies.moviecataloguedatamanagement.data.source.local.entity.MovieEntity
import com.dicoding.academies.moviecataloguedatamanagement.data.source.local.entity.TVShowEntity

@Database(entities = [MovieEntity::class, TVShowEntity::class],
    version = 1,
    exportSchema = false)
abstract class MovieCatalogueDatabase : RoomDatabase() {

    abstract fun movieCatalogueDao(): MovieCatalogueDao
    companion object {
        @Volatile
        private var INSTANCE: MovieCatalogueDatabase? = null

        fun getInstance(context: Context): MovieCatalogueDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(context.applicationContext,
                    MovieCatalogueDatabase::class.java, "movie_catalogue.db").build()
        }
    }
}