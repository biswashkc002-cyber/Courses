package com.kcbiswash.coursesassignment2

import com.kcbiswash.coursesassignment2.data.repository.FakeCourseRepository
import com.kcbiswash.coursesassignment2.ui.login.LoginViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {
    @Test
    fun loginSuccessEmitsKeypass() = runTest {
        val vm = LoginViewModel(FakeCourseRepository())
        vm.login("Biswash", "8119507")
        assertEquals("keypass123", vm.uiState.value.keypass)
    }

    @Test
    fun loginFailureEmitsError() = runTest {
        val vm = LoginViewModel(FakeCourseRepository())
        vm.login("wrong", "user")
        assertEquals(true, vm.uiState.value.error != null)
    }
}
