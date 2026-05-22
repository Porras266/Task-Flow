package com.example.taskflow.navigation

object Routes {
    const val SPLASH = "splash"
    const val HOME = "home"
    const val ADD_TASK = "add_task"
    const val EDIT_TASK = "edit_task/{taskId}"

    fun editTask(taskId: Int): String {
        return "edit_task/$taskId"
    }
}