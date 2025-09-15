package com.kcbiswash.coursesassignment2.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Course(
    val courseCode: String,
    val courseName: String,
    val instructor: String,
    val credits: Int,
    val description: String
) : Parcelable
