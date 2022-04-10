package com.tsu.roman_encoder

import android.app.Application
import android.util.Log
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tsu.roman_encoder.room.Database
import com.tsu.roman_encoder.room.Entity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class IntegrationTest {
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

    @Test
    fun viewModel_TestWithCorrectNumber() {
        val input = "3888"

        val result = getResult(input)

        assertEquals("MMMDCCCLXXXVIII", result)
    }

    @Test
    fun viewModel_TestWithIncorrectNumber() {
        val input = "0"

        val result = getResult(input)

        Log.d("Test", "before assert")
        assertEquals("Number must be greater than 0", result)

    }

    private fun getResult(input: String): String? {
        viewModel.getResult(input)

        Log.d("Test", "before return")
        return viewModel.result.value
    }
}