package com.kcbiswash.coursesassignment2.ui.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.kcbiswash.coursesassignment2.data.model.Course

@Composable
fun DashboardScreen(
    keypass: String,
    onCourseSelected: (Course) -> Unit,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadCourses(keypass)
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(uiState.courses) { course ->
            ListItem(
                headlineText = { Text(course.courseName) },
                supportingText = { Text("${course.courseCode} - ${course.instructor}") },
                modifier = Modifier.clickable { onCourseSelected(course) }
            )
            Divider()
        }
    }
}
