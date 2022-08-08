package com.berteek.criminalintent

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CrimeDetailFragmentTest {

    private lateinit var scenario: FragmentScenario<CrimeDetailFragment>

    @Before
    fun setUp() {
        scenario = launchFragmentInContainer()
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun checkIfEditTextUpdatesCrime() {

        val initialTitle = ""
        scenario.onFragment { fragment ->
            assertEquals(initialTitle, fragment.crime.title)
        }

        val testTitle = "TestTitle"
        onView(withId(R.id.crime_title)).perform(typeText(testTitle))

        scenario.onFragment { fragment ->
            assertEquals(testTitle, fragment.crime.title)
        }
    }

    @Test
    fun checkIfCheckBoxUpdatesCrime() {

        val initialIsSolved = false
        scenario.onFragment { fragment ->
            assertEquals(initialIsSolved, fragment.crime.isSolved)
        }

        val testIsSolved = true
        onView(withId(R.id.crime_solved)).perform(click())

        scenario.onFragment { fragment ->
            assertEquals(testIsSolved, fragment.crime.isSolved)
        }
    }
}