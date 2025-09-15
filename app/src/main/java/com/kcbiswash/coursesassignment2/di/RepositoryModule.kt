package com.kcbiswash.coursesassignment2.di

import com.kcbiswash.coursesassignment2.data.repository.CourseRepository
import com.kcbiswash.coursesassignment2.data.repository.FakeCourseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideCourseRepository(): CourseRepository = FakeCourseRepository()
}
