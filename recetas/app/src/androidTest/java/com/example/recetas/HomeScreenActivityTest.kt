package com.example.recetas

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasChildCount
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.recetas.utilidades.WaitForEspresso
import com.example.recetas.vistas.HomeScreenActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenActivityTest {

    @JvmField
    @Rule
    val testRule = ActivityTestRule(HomeScreenActivity::class.java)
    private val waitEspresso = WaitForEspresso()

    @Test
    fun recetaNoEncontradaTest() {
        waitEspresso.waitEspresso(5000)
        onView(withId(R.id.filtro)).perform(typeText("Ajsheyri"))
        waitEspresso.waitEspresso(3000)
        onView(withId(R.id.contenedorRecetas)).check(matches(hasChildCount(0)))
    }

    @Test
    fun recetaPorNombreEncontradaTest() {
        waitEspresso.waitEspresso(5000)
        onView(withId(R.id.filtro)).perform(typeText("Salte"))
        waitEspresso.waitEspresso(3000)
        onView(withId(R.id.contenedorRecetas)).check(matches(hasChildCount(1)))
    }

    @Test
    fun recetaPorPaisEncontradaTest() {
        waitEspresso.waitEspresso(5000)
        onView(withId(R.id.filtro)).perform(typeText("Colomb"))
        waitEspresso.waitEspresso(3000)
        onView(withId(R.id.contenedorRecetas)).check(matches(hasChildCount(1)))
    }

    @Test
    fun recetaPorIngredienteEncontradaTest() {
        waitEspresso.waitEspresso(5000)
        onView(withId(R.id.filtro)).perform(typeText("Carne"))
        waitEspresso.waitEspresso(3000)
        onView(withId(R.id.contenedorRecetas)).check(matches(hasChildCount(3)))
    }
}