package com.kcbiswash.nit3213.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kcbiswash.nit3213.data.MainRepository
import com.kcbiswash.nit3213.data.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repo: MainRepository
) : ViewModel() {

    private val _state = MutableStateFlow<Result<String>>(Result.Loading)
    val state: StateFlow<Result<String>> get() = _state

    fun doLogin(campus: String, username: String, password: String) {
        _state.value = Result.Loading
        viewModelScope.launch {
            _state.value = repo.login(campus, username, password)
        }
    }
}
