package com.kcbiswash.coursesassignment2.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kcbiswash.coursesassignment2.data.model.Course

@Composable
fun DetailsScreen(course: Course) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(course.courseName, style = MaterialTheme.typography.titleLarge)
        Text("Code: ${course.courseCode}")
        Text("Instructor: ${course.instructor}")
        Text("Credits: ${course.credits}")
        Text(course.description)
    }
}
