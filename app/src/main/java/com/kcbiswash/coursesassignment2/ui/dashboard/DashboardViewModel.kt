package com.kcbiswash.coursesassignment2.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kcbiswash.coursesassignment2.data.model.Course
import com.kcbiswash.coursesassignment2.data.repository.CourseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DashboardUiState(
    val courses: List<Course> = emptyList(),
    val loading: Boolean = false
)

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: CourseRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState

    fun loadCourses(keypass: String) {
        viewModelScope.launch {
            _uiState.value = DashboardUiState(loading = true)
            val courses = repository.getCourses(keypass)
            _uiState.value = DashboardUiState(courses = courses)
        }
    }
}
