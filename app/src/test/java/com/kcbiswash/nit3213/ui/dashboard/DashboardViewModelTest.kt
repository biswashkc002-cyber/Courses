package com.kcbiswash.nit3213.ui.dashboard

import com.google.gson.JsonObject
import com.kcbiswash.nit3213.MainDispatcherRule
import com.kcbiswash.nit3213.data.MainRepository
import com.kcbiswash.nit3213.data.Result
import com.kcbiswash.nit3213.network.DashboardResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DashboardViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @Test
    fun `load dashboard emits Success`() = runTest {
        val repo = mockk<MainRepository>()
        val entity = JsonObject().apply {
            addProperty("courseCode", "CS101")
            addProperty("description", "Intro course")
        }
        coEvery { repo.dashboard(any()) } returns Result.Success(
            DashboardResponse(entities = listOf(entity), entityTotal = 1)
        )

        val viewModel = DashboardViewModel(repo)
        viewModel.load("topicName")
        advanceUntilIdle()

        val value = viewModel.state.value
        assertTrue(value is Result.Success && value.data.entityTotal == 1)
    }
}
