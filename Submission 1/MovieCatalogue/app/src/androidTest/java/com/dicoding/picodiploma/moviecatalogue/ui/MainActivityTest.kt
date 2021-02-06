package com.dicoding.picodiploma.moviecatalogue.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.dicoding.picodiploma.moviecatalogue.R
import com.dicoding.picodiploma.moviecatalogue.utils.DataDummy.generateDummyMoviesItem
import com.dicoding.picodiploma.moviecatalogue.utils.DataDummy.generateDummyTVShowsItem
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    private val dummyMovie = generateDummyMoviesItem()
    private val dummyTvShow = generateDummyTVShowsItem()

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie))
            .perform(RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.sv_detail_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.sv_detail_movie)).perform(swipeUp())
        onView(withId(R.id.riv_item_poster_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_movie_name_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_movie_name_received))
            .check(matches(withText(dummyMovie[0].movieName)))
        onView(withId(R.id.tv_item_year_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_year_received))
            .check(matches(withText("${dummyMovie[0].year}")))
        onView(withId(R.id.tv_item_release_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_release_received)).check(matches(withText(dummyMovie[0].release)))
        onView(withId(R.id.tv_item_genre_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_genre_received)).check(matches(withText(dummyMovie[0].genre)))
        onView(withId(R.id.tv_item_duration_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_duration_received))
            .check(matches(withText(dummyMovie[0].duration)))
        onView(withId(R.id.tv_item_score_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_score_received))
            .check(matches(withText("${dummyMovie[0].score}%")))
        onView(withId(R.id.tv_item_tagLine_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_tagLine_received)).check(matches(withText(dummyMovie[0].tagLine)))
        onView(withId(R.id.tv_item_overview_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_overview_received))
            .check(matches(withText(dummyMovie[0].overview)))
        onView(withId(R.id.tv_item_person_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_person_received)).check(matches(withText(dummyMovie[0].person)))
    }

    @Test
    fun shareMovie() {
        onView(withId(R.id.rv_movie))
            .perform(RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.app_bar_share)).check(matches(isDisplayed()))
        onView(withId(R.id.app_bar_share)).check(matches(isClickable()))
        onView(withId(R.id.app_bar_share)).perform(click())
    }

    @Test
    fun loadTvShows() {
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size))
    }

    @Test
    fun loadDetailTVShow() {
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_tv_show))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size))
        onView(withId(R.id.rv_tv_show))
            .perform(RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(9, click()))
        onView(withId(R.id.sv_detail_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.sv_detail_tv_show)).perform(swipeUp())
        onView(withId(R.id.riv_item_poster_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_tv_show_name_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_tv_show_name_received))
            .check(matches(withText(dummyTvShow[9].tvShowName)))
        onView(withId(R.id.tv_item_year_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_year_received))
            .check(matches(withText(dummyTvShow[9].year.toString())))
        onView(withId(R.id.tv_item_genre_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_genre_received)).check(matches(withText(dummyTvShow[9].genre)))
        onView(withId(R.id.tv_item_duration_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_duration_received))
            .check(matches(withText(dummyTvShow[9].duration)))
        onView(withId(R.id.tv_item_score_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_score_received))
            .check(matches(withText("${dummyTvShow[9].score}%")))
        onView(withId(R.id.tv_item_tagLine_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_tagLine_received))
            .check(matches(withText(dummyTvShow[9].tagLine)))
        onView(withId(R.id.tv_item_overview_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_overview_received))
            .check(matches(withText(dummyTvShow[9].overview)))
        onView(withId(R.id.tv_item_person_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_person_received)).check(matches(withText(dummyTvShow[9].person)))
    }

    @Test
    fun shareTVShow() {
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_tv_show))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(9, click()))
        onView(withId(R.id.app_bar_share)).check(matches(isDisplayed()))
        onView(withId(R.id.app_bar_share)).check(matches(isClickable()))
        onView(withId(R.id.app_bar_share)).perform(click())
    }
}