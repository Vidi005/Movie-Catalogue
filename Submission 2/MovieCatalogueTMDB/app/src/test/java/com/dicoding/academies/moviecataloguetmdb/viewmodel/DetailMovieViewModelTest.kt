package com.dicoding.academies.moviecataloguetmdb.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.academies.moviecataloguetmdb.data.MovieCatalogueRepository
import com.dicoding.academies.moviecataloguetmdb.data.source.local.entity.DetailMovieEntity
import com.dicoding.academies.moviecataloguetmdb.utils.DataDummy.generateDummyDetailMovie
import com.dicoding.academies.moviecataloguetmdb.utils.DataDummy.generateDummyMovies
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {

    private lateinit var viewModel: DetailMovieViewModel
    private val dummyMovies = generateDummyMovies()[0]
    private val movieId = dummyMovies.id
    private val dummyDetailMovie = generateDummyDetailMovie(movieId as Int)[0]

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieCatalogueRepository: MovieCatalogueRepository

    @Mock
    private lateinit var detailMovieObserver: Observer<DetailMovieEntity>

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(movieCatalogueRepository)
        movieId?.let { viewModel.setSelectedMovie(it) }
    }

    @Test
    fun getDetailMovie() {
        val detailMovie = MutableLiveData<DetailMovieEntity>()
        detailMovie.value = dummyDetailMovie
        `when`(movieCatalogueRepository.getDetailMovie(movieId as Int)).thenReturn(detailMovie)
        val detailMovieEntity = viewModel.getDetailMovie().value as DetailMovieEntity
        verify(movieCatalogueRepository).getDetailMovie(movieId)
        assertNotNull(detailMovieEntity)
        assertEquals(dummyDetailMovie.id, detailMovieEntity.id)
        assertEquals(dummyDetailMovie.posterPath, detailMovieEntity.posterPath)
        assertEquals(dummyDetailMovie.title, detailMovieEntity.title)
        assertEquals(dummyDetailMovie.releaseDate, detailMovieEntity.releaseDate)
        assertEquals(dummyDetailMovie.genres, detailMovieEntity.genres)
        assertEquals(dummyDetailMovie.tagLine, detailMovieEntity.tagLine)
        assertEquals(dummyDetailMovie.score, detailMovieEntity.score)
        assertEquals(dummyDetailMovie.popularity, detailMovieEntity.popularity)
        assertEquals(dummyDetailMovie.overview, detailMovieEntity.overview)
        assertEquals(dummyDetailMovie.productionCompanies, detailMovieEntity.productionCompanies)
        viewModel.getDetailMovie().observeForever(detailMovieObserver)
        verify(detailMovieObserver).onChanged(dummyDetailMovie)
    }
}