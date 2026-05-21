package com.example.taskflow.data

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val priority: String,
    val isCompleted: Boolean = false
)