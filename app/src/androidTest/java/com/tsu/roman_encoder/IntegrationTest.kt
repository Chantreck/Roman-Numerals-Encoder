package com.tsu.roman_encoder

import android.app.Application
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tsu.roman_encoder.room.Database
import com.tsu.roman_encoder.room.Entity
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsNot.not
import org.hamcrest.core.IsNull.nullValue
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class IntegrationTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val appContext = ApplicationProvider.getApplicationContext<Application>()
    private val viewModel = MainViewModel(appContext)
    private val dao = Database.getInstance(appContext).getDao()

    @Before
    fun before() {
        dao.clear()
    }

    @Test
    fun database_Insert() {
        val input = "3888"

        dao.insert(Entity(input, "MMMDCCCLXXXVIII"))
        val result = dao.getOutput(input)

        assertEquals("MMMDCCCLXXXVIII", result)
    }

    /*
     Я хотел применить здесь параметризованные тесты, как в unit-тестах, но
     в данном случае их создать невозможно из-за приколов андроида
     */

    // Тесты на MainViewModel - модуль прослойки между UI-слоем и Data Layer

    @Test
    fun viewModel_WithCorrectNumber() {
        val input = "3888"

        viewModel.getResult(input)
        val result = viewModel.result.getOrAwaitValue()

        assertThat(result, `is`("MMMDCCCLXXXVIII"))
    }

    @Test
    fun viewModel_WithIncorrectNumber() {
        val input = "0"

        viewModel.getResult(input)
        val result = viewModel.result.getOrAwaitValue()

        assertThat(result, `is`("Number must be greater than 0"))
    }
}