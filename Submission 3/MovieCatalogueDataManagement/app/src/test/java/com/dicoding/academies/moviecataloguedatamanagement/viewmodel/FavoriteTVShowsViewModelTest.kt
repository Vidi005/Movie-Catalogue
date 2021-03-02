package com.dicoding.academies.moviecataloguedatamanagement.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.academies.moviecataloguedatamanagement.data.MovieCatalogueRepository
import com.dicoding.academies.moviecataloguedatamanagement.data.source.local.entity.TVShowEntity
import com.dicoding.academies.moviecataloguedatamanagement.helper.SortUtils.ASCENDING
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteTVShowsViewModelTest {

    private lateinit var viewModel: FavoriteTVShowsViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieCatalogueRepository: MovieCatalogueRepository

    @Mock
    private lateinit var observer: Observer<PagedList<TVShowEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TVShowEntity>

    @Before
    fun setUp() {
        viewModel = FavoriteTVShowsViewModel(movieCatalogueRepository)
    }

    @Test
    fun getFavoriteTVShows() {
        val dummyFavoriteTvShows = pagedList
        `when`(dummyFavoriteTvShows.size).thenReturn(5)
        val favoriteTvShows = MutableLiveData<PagedList<TVShowEntity>>()
        favoriteTvShows.value = dummyFavoriteTvShows
        `when`(movieCatalogueRepository.getFavoredTVShows(ASCENDING)).thenReturn(favoriteTvShows)
        val tvShowEntities = viewModel.getFavoriteTVShows(ASCENDING).value
        verify(movieCatalogueRepository).getFavoredTVShows(ASCENDING)
        assertNotNull(tvShowEntities)
        assertEquals(5, tvShowEntities?.size)
        viewModel.getFavoriteTVShows(ASCENDING).observeForever(observer)
        verify(observer).onChanged(dummyFavoriteTvShows)
    }
}