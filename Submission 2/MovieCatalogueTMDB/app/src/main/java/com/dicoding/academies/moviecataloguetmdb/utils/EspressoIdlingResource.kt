package com.dicoding.academies.moviecataloguetmdb.utils

import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {

    private const val RESOURCE = "GLOBAL"
    val espressoIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() = espressoIdlingResource.increment()

    fun decrement() {
        if (!espressoIdlingResource.isIdleNow) espressoIdlingResource.decrement()
    }
}
