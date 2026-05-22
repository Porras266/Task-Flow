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

    fun updateTask(
        id: Int,
        title: String,
        description: String,
        priority: String
    ) {
        _tasks.value = _tasks.value.map { task ->
            if (task.id == id) {
                task.copy(
                    title = title,
                    description = description,
                    priority = priority
                )
            } else {
                task
            }
        }
    }

    fun deleteTask(id: Int) {
        _tasks.value = _tasks.value.filter { task ->
            task.id != id
        }
    }

    fun toggleCompleted(id: Int) {
        _tasks.value = _tasks.value.map { task ->
            if (task.id == id) {
                task.copy(
                    isCompleted = !task.isCompleted
                )
            } else {
                task
            }
        }
    }

    fun getTaskById(id: Int): Task? {
        return _tasks.value.find { task ->
            task.id == id
        }
    }
}