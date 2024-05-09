package com.example.recetas

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.recetas.utilidades.WaitForEspresso
import com.example.recetas.vistas.HomeScreenActivity
import org.hamcrest.CoreMatchers.anything
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailScreenActivityTest {

    @JvmField
    @Rule
    val testRule = ActivityTestRule(HomeScreenActivity::class.java)
    private val waitEspresso = WaitForEspresso()

    @Test
    fun navegacionDetailScreenTest() {
        waitEspresso.waitEspresso(5000)
        onView(withId(R.id.filtro)).perform(typeText("Salte"))
        waitEspresso.waitEspresso(3000)
        onData(anything())
            .inAdapterView(withId(R.id.contenedorRecetas)).atPosition(0)
            .onChildView(withId(R.id.imagenReceta)).perform(click())
        waitEspresso.waitEspresso(3000)
        onView(withId(R.id.ubicacion))
            .check(matches(isDisplayed()))
    }

    @Test
    fun botonAtrasDetailScreenTest() {
        waitEspresso.waitEspresso(5000)
        onView(withId(R.id.filtro)).perform(typeText("Salte"))
        waitEspresso.waitEspresso(3000)
        onData(anything()).inAdapterView(withId(R.id.contenedorRecetas)).atPosition(0)
            .onChildView(withId(R.id.imagenReceta)).perform(click())
        waitEspresso.waitEspresso(3000)
        onView(withId(R.id.irAtras)).perform(click())
        waitEspresso.waitEspresso(3000)
        onView(withId(R.id.filtro)).check(matches(isDisplayed()))
    }
}