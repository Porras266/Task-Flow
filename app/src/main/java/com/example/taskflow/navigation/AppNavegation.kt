package com.example.taskflow.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskflow.ui.screens.AddTaskScreen
import com.example.taskflow.ui.screens.EditTaskScreen
import com.example.taskflow.ui.screens.HomeScreen
import com.example.taskflow.ui.screens.SplashScreen
import com.example.taskflow.viewmodel.TaskViewModel

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val taskViewModel: TaskViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {
        composable(Routes.SPLASH) {
            SplashScreen(navController)
        }

        composable(Routes.HOME) {
            HomeScreen(
                navController = navController,
                taskViewModel = taskViewModel
            )
        }

        composable(Routes.ADD_TASK) {
            AddTaskScreen(
                navController = navController,
                taskViewModel = taskViewModel
            )
        }

        composable(Routes.EDIT_TASK) { backStackEntry ->

            val taskId = backStackEntry.arguments
                ?.getString("taskId")
                ?.toIntOrNull()

            EditTaskScreen(
                navController = navController,
                taskViewModel = taskViewModel,
                taskId = taskId
            )
        }
    }
}