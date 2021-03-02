package com.dicoding.academies.moviecataloguedatamanagement.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.dicoding.academies.moviecataloguedatamanagement.R
import com.dicoding.academies.moviecataloguedatamanagement.utils.DataDummy.generateDummyDetailMovie
import com.dicoding.academies.moviecataloguedatamanagement.utils.DataDummy.generateDummyDetailTVShow
import com.dicoding.academies.moviecataloguedatamanagement.utils.DataDummy.generateDummyMovies
import com.dicoding.academies.moviecataloguedatamanagement.utils.DataDummy.generateDummyTVShows
import com.dicoding.academies.moviecataloguedatamanagement.utils.EspressoIdlingResource.espressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainActivityTest {

    private val dummyMovies = generateDummyMovies()
    private val movieId = dummyMovies[0].id
    private val dummyDetailMovie = generateDummyDetailMovie(movieId as Int)
    private val dummyTvShows = generateDummyTVShows()
    private val tvId = dummyTvShows[2].id
    private val dummyDetailTvShow = generateDummyDetailTVShow(tvId as Int)

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(espressoIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(espressoIdlingResource)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.navigation_home)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(19))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.sv_detail_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.riv_item_poster_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_movie_title_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_movie_title_received))
            .check(matches(withText(dummyDetailMovie[0].title)))
        onView(withId(R.id.tv_item_year_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_year_received))
            .check(matches(withText("${dummyDetailMovie[0].releaseDate?.take(4)}")))
        onView(withId(R.id.tv_item_release_date_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_release_date_received))
            .check(matches(withText(dummyDetailMovie[0].releaseDate)))
        onView(withId(R.id.tv_item_genres_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_genres_received))
            .check(matches(withText(dummyDetailMovie[0].genres)))
        onView(withId(R.id.tv_item_popularity_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_score_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_tagLine_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_tagLine_received))
            .check(matches(withText(dummyDetailMovie[0].tagLine)))
        onView(withId(R.id.tv_item_overview_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_overview_received))
            .check(matches(withText(dummyDetailMovie[0].overview)))
        onView(withId(R.id.sv_detail_movie)).perform(swipeUp())
        onView(withId(R.id.tv_item_production_companies_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_production_companies_received))
            .check(matches(withText(dummyDetailMovie[0].productionCompanies)))
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
    fun loadTVShows() {
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(19))
    }

    @Test
    fun loadDetailTVShow() {
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_tv_show))
            .perform(RecyclerViewActions
                .scrollToPosition<RecyclerView.ViewHolder>(dummyTvShows.size))
        onView(withId(R.id.rv_tv_show))
            .perform(RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
        onView(withId(R.id.sv_detail_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.riv_item_poster_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_tv_show_name_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_tv_show_name_received))
            .check(matches(withText(dummyDetailTvShow[2].name)))
        onView(withId(R.id.tv_item_first_air_date_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_first_air_date_received))
            .check(matches(withText(dummyDetailTvShow[2].firstAirDate.toString())))
        onView(withId(R.id.tv_item_genres_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_genres_received))
            .check(matches(withText(dummyDetailTvShow[2].genres)))
        onView(withId(R.id.tv_item_popularity_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_score_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_tagLine_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_tagLine_received))
            .check(matches(withText(dummyDetailTvShow[2].tagLine)))
        onView(withId(R.id.tv_item_overview_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_overview_received))
            .check(matches(withText(dummyDetailTvShow[2].overview)))
        onView(withId(R.id.sv_detail_tv_show)).perform(swipeUp())
        onView(withId(R.id.tv_item_production_companies_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_production_companies_received))
            .check(matches(withText(dummyDetailTvShow[2].productionCompanies)))
    }

    @Test
    fun shareTVShow() {
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_tv_show))
            .perform(RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
        onView(withId(R.id.app_bar_share)).check(matches(isDisplayed()))
        onView(withId(R.id.app_bar_share)).check(matches(isClickable()))
        onView(withId(R.id.app_bar_share)).perform(click())
    }

    @Test
    fun loadFavoriteMovies() {
        onView(withId(R.id.rv_movie))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.app_bar_favorite)).perform(click())
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.navigation_favorites)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_favorites)).perform(click())
        onView(withId(R.id.rv_favorite_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_favorite_movie))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.riv_item_poster_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_movie_title_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_year_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_release_date_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_genres_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_popularity_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_score_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_tagLine_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_overview_received)).check(matches(isDisplayed()))
        onView(withId(R.id.sv_detail_movie)).perform(swipeUp())
        onView(withId(R.id.tv_item_production_companies_received)).check(matches(isDisplayed()))
    }

    @Test
    fun loadFavoriteTvShows() {
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_tv_show))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(2))
        onView(withId(R.id.rv_tv_show))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
        onView(withId(R.id.app_bar_favorite)).perform(click())
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.navigation_favorites)).perform(click())
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_favorite_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_favorite_tv_show))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.riv_item_poster_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_tv_show_name_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_first_air_date_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_genres_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_popularity_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_score_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_tagLine_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_overview_received)).check(matches(isDisplayed()))
        onView(withId(R.id.sv_detail_tv_show)).perform(swipeUp())
        onView(withId(R.id.tv_item_production_companies_received)).check(matches(isDisplayed()))
    }

    @Test
    fun checkRadioButtonFavoriteMovies() {
        onView(withId(R.id.navigation_favorites)).perform(click())
        onView(withId(R.id.rg_sort_fav_movie_items)).check(matches(isDisplayed()))
        onView(withId(R.id.rb_sort_by_title_asc)).check(matches(isDisplayed()))
        onView(withId(R.id.rb_sort_by_title_asc)).check(matches(isClickable()))
        onView(withId(R.id.rb_sort_by_title_asc)).perform(click())
        onView(withId(R.id.rb_sort_by_title_asc)).check(matches(isChecked()))
        onView(withId(R.id.rb_sort_by_title_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.rb_sort_by_title_desc)).check(matches(isClickable()))
        onView(withId(R.id.rb_sort_by_title_desc)).perform(click())
        onView(withId(R.id.rb_sort_by_title_desc)).check(matches(isChecked()))
        onView(withId(R.id.rb_sort_fav_movies_by_newest)).check(matches(isDisplayed()))
        onView(withId(R.id.rb_sort_fav_movies_by_newest)).check(matches(isClickable()))
        onView(withId(R.id.rb_sort_fav_movies_by_newest)).perform(click())
        onView(withId(R.id.rb_sort_fav_movies_by_newest)).check(matches(isChecked()))
        onView(withId(R.id.rb_sort_fav_movies_by_oldest)).check(matches(isDisplayed()))
        onView(withId(R.id.rb_sort_fav_movies_by_oldest)).check(matches(isClickable()))
        onView(withId(R.id.rb_sort_fav_movies_by_oldest)).perform(click())
        onView(withId(R.id.rb_sort_fav_movies_by_oldest)).check(matches(isChecked()))
    }

    @Test
    fun checkRadioButtonFavoriteTvShows() {
        onView(withId(R.id.navigation_favorites)).perform(click())
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rg_sort_fav_tv_show_items)).check(matches(isDisplayed()))
        onView(withId(R.id.rb_sort_by_name_asc)).check(matches(isDisplayed()))
        onView(withId(R.id.rb_sort_by_name_asc)).check(matches(isClickable()))
        onView(withId(R.id.rb_sort_by_name_asc)).perform(click())
        onView(withId(R.id.rb_sort_by_name_asc)).check(matches(isChecked()))
        onView(withId(R.id.rb_sort_by_name_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.rb_sort_by_name_desc)).check(matches(isClickable()))
        onView(withId(R.id.rb_sort_by_name_desc)).perform(click())
        onView(withId(R.id.rb_sort_by_name_desc)).check(matches(isChecked()))
        onView(withId(R.id.rb_sort_fav_tv_shows_by_newest)).check(matches(isDisplayed()))
        onView(withId(R.id.rb_sort_fav_tv_shows_by_newest)).check(matches(isClickable()))
        onView(withId(R.id.rb_sort_fav_tv_shows_by_newest)).perform(click())
        onView(withId(R.id.rb_sort_fav_tv_shows_by_newest)).check(matches(isChecked()))
        onView(withId(R.id.rb_sort_fav_tv_shows_by_oldest)).check(matches(isDisplayed()))
        onView(withId(R.id.rb_sort_fav_tv_shows_by_oldest)).check(matches(isClickable()))
        onView(withId(R.id.rb_sort_fav_tv_shows_by_oldest)).perform(click())
        onView(withId(R.id.rb_sort_fav_tv_shows_by_oldest)).check(matches(isChecked()))
    }
}