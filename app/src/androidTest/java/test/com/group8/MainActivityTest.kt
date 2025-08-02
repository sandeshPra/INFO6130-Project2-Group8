package com.group8.instrumentedtesting

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest 
{

    @Rule
    @JvmField
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() 
    {
        Intents.init()
    }

    @After
    fun tearDown() 
    {
        Intents.release()
    }

    @Test
    fun testInitialTextView() 
    {
        onView(withId(R.id.textToBeChanged)).check(matches(withText("Hello from MainActivity")))
    }

    @Test
    fun testChangeTextWith123() 
    {
        onView(withId(R.id.editTextUserInput)).perform(typeText("123"), closeSoftKeyboard())
        onView(withId(R.id.changeTextBt)).perform(click())
        onView(withId(R.id.textToBeChanged)).check(matches(withText("123")))
    }

    @Test
    fun testOpenActivityWith123() 
    {
        onView(withId(R.id.editTextUserInput)).perform(typeText("123"), closeSoftKeyboard())
        onView(withId(R.id.activityChangeTextBtn)).perform(click())
        Intents.intended(hasComponent(ShowTextActivity::class.java.name))
        onView(withId(R.id.show_text_view)).check(matches(withText("123")))
    }

    @Test
    fun testChangeTextEmpty() 
    {
        onView(withId(R.id.editTextUserInput)).perform(typeText(""), closeSoftKeyboard())
        onView(withId(R.id.changeTextBt)).perform(click())
        onView(withId(R.id.textToBeChanged)).check(matches(withText("")))
    }

    @Test
    fun testOpenActivityEmpty() 
    {
        onView(withId(R.id.editTextUserInput)).perform(typeText(""), closeSoftKeyboard())
        onView(withId(R.id.activityChangeTextBtn)).perform(click())
        Intents.intended(hasComponent(ShowTextActivity::class.java.name))
        onView(withId(R.id.show_text_view)).check(matches(withText("")))
    }

    @Test
    fun testChangeTextWithAbcdef() 
    {
        onView(withId(R.id.editTextUserInput)).perform(typeText("abcdef"), closeSoftKeyboard())
        onView(withId(R.id.changeTextBt)).perform(click())
        onView(withId(R.id.textToBeChanged)).check(matches(withText("abcdef")))
    }

    @Test
    fun testOpenActivityWithAbcdef() 
    {
        onView(withId(R.id.editTextUserInput)).perform(typeText("abcdef"), closeSoftKeyboard())
        onView(withId(R.id.activityChangeTextBtn)).perform(click())
        Intents.intended(hasComponent(ShowTextActivity::class.java.name))
        onView(withId(R.id.show_text_view)).check(matches(withText("abcdef")))
    }

    @Test
    fun testShowTextActivityInitial() 
    {
        val intent = Intent(ApplicationProvider.getApplicationContext(), ShowTextActivity::class.java)
        val scenario = ActivityScenario.launch<ShowTextActivity>(intent)
        try{
            onView(withId(R.id.show_text_view)).check(matches(withText("Hello from ShowTextActivity")))
        }finally {
            scenario.close()
        }
    }
}