package com.dicoding.academies.moviecataloguedatamanagement.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dicoding.academies.moviecataloguedatamanagement.data.source.local.LocalDataSource
import com.dicoding.academies.moviecataloguedatamanagement.data.source.local.entity.MovieEntity
import com.dicoding.academies.moviecataloguedatamanagement.data.source.local.entity.TVShowEntity
import com.dicoding.academies.moviecataloguedatamanagement.data.source.remote.RemoteDataSource
import com.dicoding.academies.moviecataloguedatamanagement.helper.ListToStringHelper.movieGenreListToStringConverter
import com.dicoding.academies.moviecataloguedatamanagement.helper.ListToStringHelper.movieProductionCompanyListToStringConverter
import com.dicoding.academies.moviecataloguedatamanagement.helper.ListToStringHelper.tvShowGenreListToStringConverter
import com.dicoding.academies.moviecataloguedatamanagement.helper.ListToStringHelper.tvShowProductionCompanyListToStringConverter
import com.dicoding.academies.moviecataloguedatamanagement.helper.SortUtils.ASCENDING
import com.dicoding.academies.moviecataloguedatamanagement.utils.AppExecutors
import com.dicoding.academies.moviecataloguedatamanagement.utils.DataDummy.generateDummyDetailMovie
import com.dicoding.academies.moviecataloguedatamanagement.utils.DataDummy.generateDummyDetailTVShow
import com.dicoding.academies.moviecataloguedatamanagement.utils.DataDummy.generateDummyMovies
import com.dicoding.academies.moviecataloguedatamanagement.utils.DataDummy.generateDummyTVShows
import com.dicoding.academies.moviecataloguedatamanagement.utils.DataDummy.generateRemoteDummyDetailMovie
import com.dicoding.academies.moviecataloguedatamanagement.utils.DataDummy.generateRemoteDummyDetailTVShow
import com.dicoding.academies.moviecataloguedatamanagement.utils.DataDummy.generateRemoteDummyMoviesItem
import com.dicoding.academies.moviecataloguedatamanagement.utils.DataDummy.generateRemoteDummyTVShowsItem
import com.dicoding.academies.moviecataloguedatamanagement.utils.LiveDataTestUtil.getValue
import com.dicoding.academies.moviecataloguedatamanagement.utils.PagedListUtil.mockPagedList
import com.dicoding.academies.moviecataloguedatamanagement.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import kotlin.math.roundToInt

class MovieCatalogueRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val movieCatalogueRepository = FakeMovieCatalogueRepository(remote, local, appExecutors)
    private val movieResponses = generateRemoteDummyMoviesItem()
    private val movieId = movieResponses[0].id
    private val tvShowResponses = generateRemoteDummyTVShowsItem()
    private val tvId = tvShowResponses[2].id
    private val detailMovieResponse = generateRemoteDummyDetailMovie(movieId)[0]
    private val detailTvShowResponse = generateRemoteDummyDetailTVShow(tvId)[2]

    @Test
    @Suppress("UNCHECKED_CAST")
    fun getMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getMovies()).thenReturn(dataSourceFactory)
        movieCatalogueRepository.getMovies()
        val dummyMovieEntities = Resource.success(mockPagedList(generateDummyMovies()))
        verify(local).getMovies()
        assertNotNull(dummyMovieEntities.data)
        assertEquals(movieResponses.size, dummyMovieEntities.data?.size)
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun getTvShows() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVShowEntity>
        `when`(local.getTVShows()).thenReturn(dataSourceFactory)
        movieCatalogueRepository.getTvShows()
        val dummyTvShowEntities = Resource.success(mockPagedList(generateDummyTVShows()))
        verify(local).getTVShows()
        assertNotNull(dummyTvShowEntities)
        assertEquals(tvShowResponses.size, dummyTvShowEntities.data?.size)
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun getFavoriteMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavoredMovies(ASCENDING)).thenReturn(dataSourceFactory)
        movieCatalogueRepository.getFavoredMovies(ASCENDING)
        val dummyMovieEntities = Resource.success(mockPagedList(generateDummyMovies()))
        verify(local).getFavoredMovies(ASCENDING)
        assertNotNull(dummyMovieEntities.data)
        assertEquals(movieResponses.size, dummyMovieEntities.data?.size)
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun getFavoriteTvShows() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVShowEntity>
        `when`(local.getFavoredTVShows(ASCENDING)).thenReturn(dataSourceFactory)
        movieCatalogueRepository.getFavoredTVShows(ASCENDING)
        val dummyTvShowEntities = Resource.success(mockPagedList(generateDummyTVShows()))
        verify(local).getFavoredTVShows(ASCENDING)
        assertNotNull(dummyTvShowEntities)
        assertEquals(tvShowResponses.size, dummyTvShowEntities.data?.size)
    }

    @Test
    fun getDetailMovie() {
        val dummyMovieEntity = MutableLiveData<MovieEntity>()
        dummyMovieEntity.value = generateDummyDetailMovie(movieId)[0]
        `when`(local.getDetailMovie(movieId)).thenReturn(dummyMovieEntity)
        val detailMovieEntity = getValue(movieCatalogueRepository.getDetailMovie(movieId))
        verify(local).getDetailMovie(movieId)
        assertNotNull(detailMovieEntity)
        assertEquals(detailMovieResponse.id, detailMovieEntity.data?.id)
        assertEquals(detailMovieResponse.posterPath, detailMovieEntity.data?.posterPath)
        assertEquals(detailMovieResponse.title, detailMovieEntity.data?.title)
        assertEquals(
            movieGenreListToStringConverter(detailMovieResponse), detailMovieEntity.data?.genres)
        assertEquals(detailMovieResponse.tagline, detailMovieEntity.data?.tagLine)
        assertEquals(
            (detailMovieResponse.voteAverage * 10).roundToInt(), detailMovieEntity.data?.score)
        assertEquals(detailMovieResponse.releaseDate, detailMovieEntity.data?.releaseDate)
        assertEquals(detailMovieResponse.popularity, detailMovieEntity.data?.popularity)
        assertEquals(detailMovieResponse.overview, detailMovieEntity.data?.overview)
        assertEquals(movieProductionCompanyListToStringConverter(detailMovieResponse),
                detailMovieEntity.data?.productionCompanies)
    }

    @Test
    fun setFavoriteMovie() {
        movieCatalogueRepository.setFavoriteMovie(generateDummyDetailMovie(movieId)[0], true)
        verify(local).setMovieFavorite(generateDummyDetailMovie(movieId)[0], true)
        verifyNoMoreInteractions(local)
    }

    @Test
    fun getDetailTVShow() {
        val dummyTvShowEntity = MutableLiveData<TVShowEntity>()
        dummyTvShowEntity.value = generateDummyDetailTVShow(tvId)[2]
        `when`(local.getDetailTVShow(tvId)).thenReturn(dummyTvShowEntity)
        val detailTVShowEntity = getValue(movieCatalogueRepository.getDetailTVShow(tvId))
        verify(local).getDetailTVShow(tvId)
        assertNotNull(detailTVShowEntity)
        assertEquals(detailTvShowResponse.id, detailTVShowEntity.data?.id)
        assertEquals(detailTvShowResponse.posterPath, detailTVShowEntity.data?.posterPath)
        assertEquals(detailTvShowResponse.name, detailTVShowEntity.data?.name)
        assertEquals(
            tvShowGenreListToStringConverter(detailTvShowResponse), detailTVShowEntity.data?.genres)
        assertEquals(detailTvShowResponse.tagline, detailTVShowEntity.data?.tagLine)
        assertEquals(
            (detailTvShowResponse.voteAverage * 10).roundToInt(), detailTVShowEntity.data?.score)
        assertEquals(detailTvShowResponse.firstAirDate, detailTVShowEntity.data?.firstAirDate)
        assertEquals(detailTvShowResponse.popularity, detailTVShowEntity.data?.popularity)
        assertEquals(detailTvShowResponse.overview, detailTVShowEntity.data?.overview)
        assertEquals(
            tvShowProductionCompanyListToStringConverter(detailTvShowResponse),
                detailTVShowEntity.data?.productionCompanies)
    }

    @Test
    fun setFavoriteTvShow() {
        movieCatalogueRepository.setFavoriteTVShow(generateDummyDetailTVShow(tvId)[2], true)
        verify(local).setTVShowFavorite(generateDummyDetailTVShow(tvId)[2], true)
        verifyNoMoreInteractions(local)
    }
}
