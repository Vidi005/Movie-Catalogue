package com.dicoding.academies.moviecataloguetmdb.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.academies.moviecataloguetmdb.data.MovieCatalogueRepository
import com.dicoding.academies.moviecataloguetmdb.data.source.local.entity.MovieEntity
import com.dicoding.academies.moviecataloguetmdb.utils.DataDummy.generateDummyMovies
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {

    private lateinit var viewModel: MoviesViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieCatalogueRepository: MovieCatalogueRepository

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(movieCatalogueRepository)
    }

    @Test
    fun getMovies() {
        val dummyMovies = generateDummyMovies()
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovies
        `when`(movieCatalogueRepository.getMovies()).thenReturn(movies)
        val movieEntities = viewModel.getMovies().value
        verify(movieCatalogueRepository).getMovies()
        assertNotNull(movieEntities)
        assertEquals(3, movieEntities?.size)
        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}