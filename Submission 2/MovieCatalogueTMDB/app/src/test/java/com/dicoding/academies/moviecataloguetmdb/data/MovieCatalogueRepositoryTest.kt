package com.dicoding.academies.moviecataloguetmdb.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dicoding.academies.moviecataloguetmdb.data.source.remote.RemoteDataSource
import com.dicoding.academies.moviecataloguetmdb.helper.ListToStringHelper.movieGenreListToStringConverter
import com.dicoding.academies.moviecataloguetmdb.helper.ListToStringHelper.movieProductionCompanyListToStringConverter
import com.dicoding.academies.moviecataloguetmdb.helper.ListToStringHelper.tvShowGenreListToStringConverter
import com.dicoding.academies.moviecataloguetmdb.helper.ListToStringHelper.tvShowProductionCompanyListToStringConverter
import com.dicoding.academies.moviecataloguetmdb.utils.DataDummy.generateRemoteDummyDetailMovie
import com.dicoding.academies.moviecataloguetmdb.utils.DataDummy.generateRemoteDummyDetailTVShow
import com.dicoding.academies.moviecataloguetmdb.utils.DataDummy.generateRemoteDummyMoviesItem
import com.dicoding.academies.moviecataloguetmdb.utils.DataDummy.generateRemoteDummyTVShowsItem
import com.dicoding.academies.moviecataloguetmdb.utils.LiveDataTestUtil.getValue
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import kotlin.math.roundToInt

class MovieCatalogueRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val movieCatalogueRepository = FakeMovieCatalogueRepository(remote)
    private val movieResponses = generateRemoteDummyMoviesItem()
    private val movieId = movieResponses[0].id
    private val tvShowResponses = generateRemoteDummyTVShowsItem()
    private val tvId = tvShowResponses[0].id
    private val detailMovieResponse = generateRemoteDummyDetailMovie(movieId)[0]
    private val detailTvShowResponse = generateRemoteDummyDetailTVShow(tvId)[0]

    @Test
    fun getMovies() {
        doAnswer {
            (it.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                .onMoviesReceived(movieResponses)
            null
        }.`when`(remote).getMovies(any())
        val movieEntities = getValue(movieCatalogueRepository.getMovies())
        verify(remote).getMovies(any())
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size, movieEntities.size)
    }

    @Test
    fun getTvShows() {
        doAnswer {
            (it.arguments[0] as RemoteDataSource.LoadTvShowsCallback)
                .onTvShowsReceived(tvShowResponses)
            null
        }.`when`(remote).getTvShows(any())
        val tvShowEntities = getValue(movieCatalogueRepository.getTvShows())
        verify(remote).getTvShows(any())
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponses.size, tvShowEntities.size)
    }

    @Test
    fun getDetailMovie() {
        doAnswer {
            (it.arguments[1] as RemoteDataSource.LoadDetailMovieCallback)
                .onDetailMovieReceived(detailMovieResponse)
            null
        }.`when`(remote).getDetailMovie(eq(movieId), any())
        val detailMovieEntity = getValue(movieCatalogueRepository.getDetailMovie(movieId))
        verify(remote).getDetailMovie(eq(movieId), any())
        assertNotNull(detailMovieEntity)
        assertEquals(detailMovieResponse.id, detailMovieEntity.id)
        assertEquals(detailMovieResponse.posterPath, detailMovieEntity.posterPath)
        assertEquals(detailMovieResponse.title, detailMovieEntity.title)
        assertEquals(movieGenreListToStringConverter(detailMovieResponse).toString(),
                detailMovieEntity.genres)
        assertEquals(detailMovieResponse.tagline, detailMovieEntity.tagLine)
        assertEquals((detailMovieResponse.voteAverage * 10).roundToInt(), detailMovieEntity.score)
        assertEquals(detailMovieResponse.releaseDate, detailMovieEntity.releaseDate)
        assertEquals(detailMovieResponse.popularity, detailMovieEntity.popularity)
        assertEquals(detailMovieResponse.overview, detailMovieEntity.overview)
        assertEquals(movieProductionCompanyListToStringConverter(detailMovieResponse).toString(),
                detailMovieEntity.productionCompanies)
    }

    @Test
    fun getDetailTVShow() {
        doAnswer {
            (it.arguments[1] as RemoteDataSource.LoadDetailTvShowCallback)
                .onDetailTVShowReceived(detailTvShowResponse)
            null
        }.`when`(remote).getDetailTVShow(eq(tvId), any())
        val detailTVShowEntity = getValue(movieCatalogueRepository.getDetailTVShow(tvId))
        verify(remote).getDetailTVShow(eq(tvId), any())
        assertNotNull(detailTVShowEntity)
        assertEquals(detailTvShowResponse.id, detailTVShowEntity.id)
        assertEquals(detailTvShowResponse.posterPath, detailTVShowEntity.posterPath)
        assertEquals(detailTvShowResponse.name, detailTVShowEntity.name)
        assertEquals(tvShowGenreListToStringConverter(detailTvShowResponse).toString(),
                detailTVShowEntity.genres)
        assertEquals(detailTvShowResponse.tagline, detailTVShowEntity.tagLine)
        assertEquals((detailTvShowResponse.voteAverage * 10).roundToInt(), detailTVShowEntity.score)
        assertEquals(detailTvShowResponse.firstAirDate, detailTVShowEntity.firstAirDate)
        assertEquals(detailTvShowResponse.popularity, detailTVShowEntity.popularity)
        assertEquals(detailTvShowResponse.overview, detailTVShowEntity.overview)
        assertEquals(tvShowProductionCompanyListToStringConverter(detailTvShowResponse).toString(),
                detailTVShowEntity.productionCompanies)
    }
}