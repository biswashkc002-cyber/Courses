package com.kcbiswash.coursesassignment2.data.repository

import com.kcbiswash.coursesassignment2.data.model.Course

interface CourseRepository {
    suspend fun login(username: String, password: String): Result<String>
    suspend fun getCourses(keypass: String): List<Course>
}
