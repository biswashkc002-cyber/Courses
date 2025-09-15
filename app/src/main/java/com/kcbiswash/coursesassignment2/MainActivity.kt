package com.kcbiswash.coursesassignment2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kcbiswash.coursesassignment2.data.model.Course
import com.kcbiswash.coursesassignment2.ui.dashboard.DashboardScreen
import com.kcbiswash.coursesassignment2.ui.details.DetailsScreen
import com.kcbiswash.coursesassignment2.ui.login.LoginScreen
import com.kcbiswash.coursesassignment2.ui.theme.Coursesassignment2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Coursesassignment2Theme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "login") {
        composable("login") {
            LoginScreen(onSuccess = { keypass ->
                navController.navigate("dashboard/$keypass")
            })
        }
        composable(
            route = "dashboard/{keypass}",
            arguments = listOf(navArgument("keypass") { type = NavType.StringType })
        ) { backStackEntry ->
            val keypass = backStackEntry.arguments?.getString("keypass").orEmpty()
            DashboardScreen(keypass = keypass, onCourseSelected = { course ->
                navController.currentBackStackEntry?.savedStateHandle?.set("course", course)
                navController.navigate("details")
            })
        }
        composable("details") {
            val course = navController.previousBackStackEntry?.savedStateHandle?.get<Course>("course")
            course?.let { DetailsScreen(it) }
        }
    }
}
