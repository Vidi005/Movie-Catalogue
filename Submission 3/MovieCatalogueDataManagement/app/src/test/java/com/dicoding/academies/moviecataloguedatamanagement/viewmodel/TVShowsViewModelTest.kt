package com.dicoding.academies.moviecataloguedatamanagement.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.academies.moviecataloguedatamanagement.data.MovieCatalogueRepository
import com.dicoding.academies.moviecataloguedatamanagement.data.source.local.entity.TVShowEntity
import com.dicoding.academies.moviecataloguedatamanagement.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TVShowsViewModelTest {

    private lateinit var viewModel: TVShowsViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieCatalogueRepository: MovieCatalogueRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TVShowEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TVShowEntity>

    @Before
    fun setUp() {
        viewModel = TVShowsViewModel(movieCatalogueRepository)
    }

    @Test
    fun getTvShows() {
        val dummyTvShows = Resource.success(pagedList)
        `when`(dummyTvShows.data?.size).thenReturn(3)
        val tvShows = MutableLiveData<Resource<PagedList<TVShowEntity>>>()
        tvShows.value = dummyTvShows
        `when`(movieCatalogueRepository.getTvShows()).thenReturn(tvShows)
        val tvShowEntities = viewModel.getTvShows().value?.data
        verify(movieCatalogueRepository).getTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(3, tvShowEntities?.size)
        viewModel.getTvShows().observeForever(observer)
        verify(observer).onChanged(dummyTvShows)
    }
}