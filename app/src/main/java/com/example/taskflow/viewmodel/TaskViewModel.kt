package com.example.taskflow.viewmodel

import androidx.lifecycle.ViewModel
import com.example.taskflow.data.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TaskViewModel : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())

    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    fun addTask(
        title: String,
        description: String,
        priority: String
    ) {
        val newTask = Task(
            id = (_tasks.value.maxOfOrNull { it.id } ?: 0) + 1,
            title = title,
            description = description,
            priority = priority
        )

        _tasks.value = _tasks.value + newTask
    }

    fun deleteTask(id: Int) {
        _tasks.value = _tasks.value.filter {
            it.id != id
        }
    }

    fun toggleCompleted(id: Int) {
        _tasks.value = _tasks.value.map {
            if (it.id == id) {
                it.copy(isCompleted = !it.isCompleted)
            } else {
                it
            }
        }
    }
}