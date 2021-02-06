package com.dicoding.picodiploma.moviecatalogue.viewmodel

import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class TvShowsViewModelTest {

    private lateinit var viewModel: TvShowsViewModel

    @Before
    fun setUp() {
        viewModel = TvShowsViewModel()
    }

    @Test
    fun getTvShows() {
        val tvShowEntities = viewModel.getTVShows()
        assertNotNull(tvShowEntities)
        assertEquals(10, tvShowEntities.size)
    }
}