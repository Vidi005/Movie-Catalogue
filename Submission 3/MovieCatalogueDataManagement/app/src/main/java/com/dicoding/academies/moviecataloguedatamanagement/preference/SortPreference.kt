package com.dicoding.academies.moviecataloguedatamanagement.preference

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

internal class SortPreference {

    private lateinit var preferences: SharedPreferences
    companion object {
        private const val PREFS_SORT_MOVIES_BY_TITLE_ASC = "sort_movies_by_title_asc_pref"
        private const val PREFS_SORT_MOVIES_BY_TITLE_DESC = "sort_movies_by_title_desc_pref"
        private const val PREFS_SORT_MOVIES_BY_NEWEST = "sort_movies_by_newest_pref"
        private const val PREFS_SORT_MOVIES_BY_OLDEST = "sort_movies_by_oldest_pref"
        private const val PREFS_SORT_TV_SHOWS_BY_NAME_ASC = "sort_tv_shows_by_name_asc_pref"
        private const val PREFS_SORT_TV_SHOWS_BY_NAME_DESC = "sort_tv_shows_by_name_desc_pref"
        private const val PREFS_SORT_TV_SHOWS_BY_NEWEST = "sort_tv_shows_by_newest_pref"
        private const val PREFS_SORT_TV_SHOWS_BY_OLDEST = "sort_tv_shows_by_oldest_pref"
    }

    fun setSortMoviesByTitleAsc(context: Context) {
        preferences = context.getSharedPreferences(
                PREFS_SORT_MOVIES_BY_TITLE_ASC, Context.MODE_PRIVATE) as SharedPreferences
        preferences.edit { putBoolean(PREFS_SORT_MOVIES_BY_TITLE_ASC, true) }
    }

    fun getSortMoviesByTitleAsc(context: Context): Boolean {
        preferences = context.getSharedPreferences(
                PREFS_SORT_MOVIES_BY_TITLE_ASC, Context.MODE_PRIVATE) as SharedPreferences
        return preferences.getBoolean(PREFS_SORT_MOVIES_BY_TITLE_ASC, true)
    }

    fun clearSortMoviesByTitleAsc(context: Context) {
        preferences = context.getSharedPreferences(
                PREFS_SORT_MOVIES_BY_TITLE_ASC, Context.MODE_PRIVATE) as SharedPreferences
        preferences.edit { putBoolean(PREFS_SORT_MOVIES_BY_TITLE_ASC, false) }
    }

    fun setSortMoviesByTitleDesc(context: Context) {
        preferences = context.getSharedPreferences(
                PREFS_SORT_MOVIES_BY_TITLE_DESC, Context.MODE_PRIVATE) as SharedPreferences
        preferences.edit { putBoolean(PREFS_SORT_MOVIES_BY_TITLE_DESC, true) }
    }

    fun getSortMoviesByTitleDesc(context: Context): Boolean {
        preferences = context.getSharedPreferences(
                PREFS_SORT_MOVIES_BY_TITLE_DESC, Context.MODE_PRIVATE) as SharedPreferences
        return preferences.getBoolean(PREFS_SORT_MOVIES_BY_TITLE_DESC, false)
    }

    fun clearSortMoviesByTitleDesc(context: Context) {
        preferences = context.getSharedPreferences(
                PREFS_SORT_MOVIES_BY_TITLE_DESC, Context.MODE_PRIVATE) as SharedPreferences
        preferences.edit { clear() }
    }

    fun setSortMoviesByNewest(context: Context) {
        preferences = context.getSharedPreferences(
                PREFS_SORT_MOVIES_BY_NEWEST, Context.MODE_PRIVATE) as SharedPreferences
        preferences.edit { putBoolean(PREFS_SORT_MOVIES_BY_NEWEST, true) }
    }

    fun getSortMoviesByNewest(context: Context): Boolean {
        preferences = context.getSharedPreferences(
                PREFS_SORT_MOVIES_BY_NEWEST, Context.MODE_PRIVATE) as SharedPreferences
        return preferences.getBoolean(PREFS_SORT_MOVIES_BY_NEWEST, false)
    }

    fun clearSortMoviesByNewest(context: Context) {
        preferences = context.getSharedPreferences(
                PREFS_SORT_MOVIES_BY_NEWEST, Context.MODE_PRIVATE) as SharedPreferences
        preferences.edit { clear() }
    }

    fun setSortMoviesByOldest(context: Context) {
        preferences = context.getSharedPreferences(
                PREFS_SORT_MOVIES_BY_OLDEST, Context.MODE_PRIVATE) as SharedPreferences
        preferences.edit { putBoolean(PREFS_SORT_MOVIES_BY_OLDEST, true) }
    }

    fun getSortMoviesByOldest(context: Context): Boolean {
        preferences = context.getSharedPreferences(
                PREFS_SORT_MOVIES_BY_OLDEST, Context.MODE_PRIVATE) as SharedPreferences
        return preferences.getBoolean(PREFS_SORT_MOVIES_BY_OLDEST, false)
    }

    fun clearSortMoviesByOldest(context: Context) {
        preferences = context.getSharedPreferences(
                PREFS_SORT_MOVIES_BY_OLDEST, Context.MODE_PRIVATE) as SharedPreferences
        preferences.edit { clear() }
    }

    fun setSortTvShowsByNameAsc(context: Context) {
        preferences = context.getSharedPreferences(
                PREFS_SORT_TV_SHOWS_BY_NAME_ASC, Context.MODE_PRIVATE) as SharedPreferences
        preferences.edit { putBoolean(PREFS_SORT_TV_SHOWS_BY_NAME_ASC, true) }
    }

    fun getSortTvShowsByNameAsc(context: Context): Boolean {
        preferences = context.getSharedPreferences(
                PREFS_SORT_TV_SHOWS_BY_NAME_ASC, Context.MODE_PRIVATE) as SharedPreferences
        return preferences.getBoolean(PREFS_SORT_TV_SHOWS_BY_NAME_ASC, true)
    }

    fun clearSortTvShowsByNameAsc(context: Context) {
        preferences = context.getSharedPreferences(
                PREFS_SORT_TV_SHOWS_BY_NAME_ASC, Context.MODE_PRIVATE) as SharedPreferences
        preferences.edit { putBoolean(PREFS_SORT_TV_SHOWS_BY_NAME_ASC, false) }
    }

    fun setSortTvShowsByNameDesc(context: Context) {
        preferences = context.getSharedPreferences(
                PREFS_SORT_TV_SHOWS_BY_NAME_DESC, Context.MODE_PRIVATE) as SharedPreferences
        preferences.edit { putBoolean(PREFS_SORT_TV_SHOWS_BY_NAME_DESC, true) }
    }

    fun getSortTvShowsByNameDesc(context: Context): Boolean {
        preferences = context.getSharedPreferences(
                PREFS_SORT_TV_SHOWS_BY_NAME_DESC, Context.MODE_PRIVATE) as SharedPreferences
        return preferences.getBoolean(PREFS_SORT_TV_SHOWS_BY_NAME_DESC, false)
    }

    fun clearSortTvShowsByNameDesc(context: Context) {
        preferences = context.getSharedPreferences(
                PREFS_SORT_TV_SHOWS_BY_NAME_DESC, Context.MODE_PRIVATE) as SharedPreferences
        preferences.edit { clear() }
    }

    fun setSortTvShowsByNewest(context: Context) {
        preferences = context.getSharedPreferences(
                PREFS_SORT_TV_SHOWS_BY_NEWEST, Context.MODE_PRIVATE) as SharedPreferences
        preferences.edit { putBoolean(PREFS_SORT_TV_SHOWS_BY_NEWEST, true) }
    }

    fun getSortTvShowsByNewest(context: Context): Boolean {
        preferences = context.getSharedPreferences(
                PREFS_SORT_TV_SHOWS_BY_NEWEST, Context.MODE_PRIVATE) as SharedPreferences
        return preferences.getBoolean(PREFS_SORT_TV_SHOWS_BY_NEWEST, false)
    }

    fun clearSortTvShowsByNewest(context: Context) {
        preferences = context.getSharedPreferences(
                PREFS_SORT_TV_SHOWS_BY_NEWEST, Context.MODE_PRIVATE) as SharedPreferences
        preferences.edit { clear() }
    }

    fun setSortTvShowsByOldest(context: Context) {
        preferences = context.getSharedPreferences(
                PREFS_SORT_TV_SHOWS_BY_OLDEST, Context.MODE_PRIVATE) as SharedPreferences
        preferences.edit { putBoolean(PREFS_SORT_TV_SHOWS_BY_OLDEST, true) }
    }

    fun getSortTvShowsByOldest(context: Context): Boolean {
        preferences = context.getSharedPreferences(
                PREFS_SORT_TV_SHOWS_BY_OLDEST, Context.MODE_PRIVATE) as SharedPreferences
        return preferences.getBoolean(PREFS_SORT_TV_SHOWS_BY_OLDEST, false)
    }

    fun clearSortTvShowsByOldest(context: Context) {
        preferences = context.getSharedPreferences(
                PREFS_SORT_TV_SHOWS_BY_OLDEST, Context.MODE_PRIVATE) as SharedPreferences
        preferences.edit { clear() }
    }
}