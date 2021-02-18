package com.dicoding.academies.moviecataloguetmdb.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.academies.moviecataloguetmdb.data.MovieCatalogueRepository
import com.dicoding.academies.moviecataloguetmdb.data.source.local.entity.DetailTVShowEntity
import com.dicoding.academies.moviecataloguetmdb.utils.DataDummy.generateDummyDetailTVShow
import com.dicoding.academies.moviecataloguetmdb.utils.DataDummy.generateDummyTVShows
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
class DetailTVShowViewModelTest {

    private lateinit var viewModel: DetailTVShowViewModel
    private val dummyTvShows = generateDummyTVShows()[2]
    private val tvId = dummyTvShows.id
    private val dummyDetailTvShow = generateDummyDetailTVShow(tvId as Int)[2]

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieCatalogueRepository: MovieCatalogueRepository

    @Mock
    private lateinit var detailTvShowObserver: Observer<DetailTVShowEntity>

    @Before
    fun setUp() {
        viewModel = DetailTVShowViewModel(movieCatalogueRepository)
        tvId?.let { viewModel.setSelectedTVShow(it) }
    }

    @Test
    fun getDetailTVShow() {
        val detailTvShow = MutableLiveData<DetailTVShowEntity>()
        detailTvShow.value = dummyDetailTvShow
        `when`(movieCatalogueRepository.getDetailTVShow(tvId as Int)).thenReturn(detailTvShow)
        val detailTVShowEntity = viewModel.getDetailTVShow().value as DetailTVShowEntity
        verify(movieCatalogueRepository).getDetailTVShow(tvId)
        assertNotNull(detailTVShowEntity)
        assertEquals(dummyDetailTvShow.id, detailTVShowEntity.id)
        assertEquals(dummyDetailTvShow.posterPath, detailTVShowEntity.posterPath)
        assertEquals(dummyDetailTvShow.name, detailTVShowEntity.name)
        assertEquals(dummyDetailTvShow.firstAirDate, detailTVShowEntity.firstAirDate)
        assertEquals(dummyDetailTvShow.genres, detailTVShowEntity.genres)
        assertEquals(dummyDetailTvShow.tagLine, detailTVShowEntity.tagLine)
        assertEquals(dummyDetailTvShow.score, detailTVShowEntity.score)
        assertEquals(dummyDetailTvShow.popularity, detailTVShowEntity.popularity)
        assertEquals(dummyDetailTvShow.overview, detailTVShowEntity.overview)
        assertEquals(dummyDetailTvShow.productionCompanies, detailTVShowEntity.productionCompanies)
        viewModel.getDetailTVShow().observeForever(detailTvShowObserver)
        verify(detailTvShowObserver).onChanged(dummyDetailTvShow)
    }
}