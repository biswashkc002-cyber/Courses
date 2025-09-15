package com.kcbiswash.coursesassignment2.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kcbiswash.coursesassignment2.data.repository.CourseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginUiState(
    val keypass: String? = null,
    val error: String? = null,
    val loading: Boolean = false
)

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: CourseRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _uiState.value = LoginUiState(loading = true)
            val result = repository.login(username, password)
            _uiState.value = result.fold(
                onSuccess = { LoginUiState(keypass = it) },
                onFailure = { LoginUiState(error = it.message) }
            )
        }
    }
}
