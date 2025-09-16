@file:Suppress("DSL_SCOPE_VIOLATION")

import org.gradle.accessors.dm.LibrariesForLibs

// Top-level build file where you can add configuration options common to all sub-projects/modules.

val libs = the<LibrariesForLibs>()

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
}
