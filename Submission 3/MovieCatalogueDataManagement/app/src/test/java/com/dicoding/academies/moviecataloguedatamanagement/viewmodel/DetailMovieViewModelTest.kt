package com.dicoding.academies.moviecataloguedatamanagement.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.academies.moviecataloguedatamanagement.data.MovieCatalogueRepository
import com.dicoding.academies.moviecataloguedatamanagement.data.source.local.entity.MovieEntity
import com.dicoding.academies.moviecataloguedatamanagement.utils.DataDummy.generateDummyDetailMovie
import com.dicoding.academies.moviecataloguedatamanagement.utils.DataDummy.generateDummyMovies
import com.dicoding.academies.moviecataloguedatamanagement.vo.Resource
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
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
    private lateinit var detailMovieObserver: Observer<Resource<MovieEntity>>

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(movieCatalogueRepository)
        movieId?.let { viewModel.setSelectedMovie(it) }
    }

    @Test
    fun getDetailMovie() {
        val dummyDetailMovie = Resource.success(dummyDetailMovie)
        val detailMovie = MutableLiveData<Resource<MovieEntity>>()
        detailMovie.value = dummyDetailMovie
        `when`(movieCatalogueRepository.getDetailMovie(movieId as Int)).thenReturn(detailMovie)
        viewModel.detailMovie.observeForever(detailMovieObserver)
        assertNotNull(dummyDetailMovie.data)
        verify(detailMovieObserver).onChanged(dummyDetailMovie)
    }

    @Test
    fun setFavoriteMovie() {
        val dummyDetailMovie = Resource.success(dummyDetailMovie)
        val detailMovie = MutableLiveData<Resource<MovieEntity>>()
        detailMovie.value = dummyDetailMovie
        viewModel.detailMovie = detailMovie
        dummyDetailMovie.data
            ?.let { doNothing().`when`(movieCatalogueRepository).setFavoriteMovie(it, true) }
        viewModel.setFavoriteMovie()
        verify(movieCatalogueRepository)
            .setFavoriteMovie(detailMovie.value?.data as MovieEntity, true)
    }
}
