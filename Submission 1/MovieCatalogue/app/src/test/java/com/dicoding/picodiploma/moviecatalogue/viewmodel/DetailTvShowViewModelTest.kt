package com.dicoding.picodiploma.moviecatalogue.viewmodel

import com.dicoding.picodiploma.moviecatalogue.utils.DataDummy.generateDummyTVShowsItem
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class DetailTvShowViewModelTest {

    private lateinit var viewModel: DetailTvShowViewModel
    private val dummyTvShow = generateDummyTVShowsItem()[9]
    private val tvShowName = dummyTvShow.tvShowName

    @Before
    fun setUp() {
        viewModel = DetailTvShowViewModel()
        viewModel.setSelectedTvShow(tvShowName as String)
    }

    @Test
    fun getDetailTvShow() {
        tvShowName?.let { viewModel.setSelectedTvShow(it) }
        val tvShowEntity = viewModel.getDetailTvShow()
        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShow.poster, tvShowEntity.poster)
        assertEquals(dummyTvShow.tvShowName, tvShowEntity.tvShowName)
        assertEquals(dummyTvShow.year, tvShowEntity.year)
        assertEquals(dummyTvShow.genre, tvShowEntity.genre)
        assertEquals(dummyTvShow.duration, tvShowEntity.duration)
        assertEquals(dummyTvShow.score, tvShowEntity.score)
        assertEquals(dummyTvShow.tagLine, tvShowEntity.tagLine)
        assertEquals(dummyTvShow.overview, tvShowEntity.overview)
        assertEquals(dummyTvShow.person, tvShowEntity.person)
    }
}