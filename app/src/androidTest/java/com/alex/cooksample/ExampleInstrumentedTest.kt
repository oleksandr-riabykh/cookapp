package com.alex.cooksample

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class CollectionsFlowTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testEvent() {
        val scenario = launchActivity<MainActivity>()
    }

    @Test
    fun listIsDisplayed() {
        onView(withId(R.id.root_recycler)).check(matches(isDisplayed()))
    }

    @Test(expected = NoMatchingViewException::class)
    fun itemWithText_doesNotExist() {
        val fakeText = "sgrsgdv dr rg r"
        onData(withText(fakeText))
            .onChildView(withId(R.id.collectionTitleTextView))
            .check(doesNotExist())
    }

    @Test
    fun navigateToRecipesPositive() {

    }
}