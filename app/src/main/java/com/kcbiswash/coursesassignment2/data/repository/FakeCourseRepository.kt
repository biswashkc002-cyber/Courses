package com.kcbiswash.coursesassignment2.data.repository

import com.kcbiswash.coursesassignment2.data.model.Course
import kotlinx.coroutines.delay

class FakeCourseRepository : CourseRepository {
    private val courses = listOf(
        Course(
            "CS101",
            "Introduction to Programming",
            "Dr. Smith",
            3,
            "A foundational course covering basic programming concepts and problem-solving techniques."
        ),
        Course(
            "MATH201",
            "Calculus II",
            "Prof. Johnson",
            4,
            "Advanced calculus topics including integration techniques, series, and multivariable calculus."
        ),
        Course(
            "ENG105",
            "Creative Writing",
            "Ms. Brown",
            3,
            "Exploration of various creative writing genres, focusing on developing personal style and voice."
        ),
        Course(
            "PHYS301",
            "Quantum Mechanics",
            "Dr. Lee",
            4,
            "Introduction to quantum theory, wave functions, and applications in modern physics."
        ),
        Course(
            "BIO202",
            "Genetics",
            "Prof. Garcia",
            3,
            "Study of heredity, gene expression, and genetic disorders in various organisms."
        ),
        Course(
            "CHEM101",
            "General Chemistry",
            "Dr. Wilson",
            4,
            "Fundamental principles of chemistry, atomic structure, and chemical reactions."
        ),
        Course(
            "PSYCH205",
            "Cognitive Psychology",
            "Prof. Taylor",
            3,
            "Examination of mental processes including perception, memory, problem-solving, and decision-making."
        )
    )

    override suspend fun login(username: String, password: String): Result<String> {
        delay(500) // simulate network
        return if (username == "Biswash" && password == "8119507") {
            Result.success("keypass123")
        } else {
            Result.failure(IllegalArgumentException("Invalid credentials"))
        }
    }

    override suspend fun getCourses(keypass: String): List<Course> {
        delay(500)
        return if (keypass == "keypass123") courses else emptyList()
    }
}
