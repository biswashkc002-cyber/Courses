package com.kcbiswash.nit3213.ui.login

import com.kcbiswash.nit3213.MainDispatcherRule
import com.kcbiswash.nit3213.data.MainRepository
import com.kcbiswash.nit3213.data.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @Test
    fun `login success emits Success`() = runTest {
        val repo = mockk<MainRepository>()
        coEvery { repo.login(any(), any(), any()) } returns Result.Success("topicName")
        val viewModel = LoginViewModel(repo)

        viewModel.doLogin("br", "Alice", "12345678")
        advanceUntilIdle()

        val value = viewModel.state.value
        assertTrue(value is Result.Success && value.data == "topicName")
    }

    @Test
    fun `login failure emits Error`() = runTest {
        val repo = mockk<MainRepository>()
        coEvery { repo.login(any(), any(), any()) } returns Result.Error("bad creds")
        val viewModel = LoginViewModel(repo)

        viewModel.doLogin("br", "Bob", "0000")
        advanceUntilIdle()

        assertTrue(viewModel.state.value is Result.Error)
    }
}
