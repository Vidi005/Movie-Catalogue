package com.dicoding.academies.moviecataloguedatamanagement.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.dicoding.academies.moviecataloguedatamanagement.data.source.local.entity.*

@Dao
interface MovieCatalogueDao {

    @Query("SELECT * FROM movie_entity ORDER BY popularity DESC")
    fun getMovies(): DataSource.Factory<Int, MovieEntity>

    @RawQuery(observedEntities = [MovieEntity::class])
    fun getFavoriteMovies(query: SupportSQLiteQuery): DataSource.Factory<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movie: List<MovieEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)

    @Query("SELECT * FROM movie_entity WHERE id = :id")
    fun getDetailMovieById(id: Int): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailMovie(detailMovie: MovieEntity)


    @Query("SELECT * FROM tv_show_entity ORDER BY popularity DESC")
    fun getTVShows(): DataSource.Factory<Int, TVShowEntity>

    @RawQuery(observedEntities = [TVShowEntity::class])
    fun getFavoriteTVShows(query: SupportSQLiteQuery): DataSource.Factory<Int, TVShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTVShows(tvShow: List<TVShowEntity>)

    @Update
    fun updateTVShow(tvShow: TVShowEntity)

    @Query("SELECT * FROM tv_show_entity WHERE id = :id")
    fun getDetailTVShowById(id: Int): LiveData<TVShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailTVShow(detailTVShow: TVShowEntity)
}