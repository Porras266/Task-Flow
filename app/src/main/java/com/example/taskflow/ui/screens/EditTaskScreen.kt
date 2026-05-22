package com.example.taskflow.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskflow.navigation.Routes
import com.example.taskflow.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTaskScreen(
    navController: NavController,
    taskViewModel: TaskViewModel,
    taskId: Int?
) {
    val task = taskId?.let {
        taskViewModel.getTaskById(it)
    }

    var title by remember {
        mutableStateOf(task?.title ?: "")
    }

    var description by remember {
        mutableStateOf(task?.description ?: "")
    }

    var priority by remember {
        mutableStateOf(task?.priority ?: "")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Editar tarea")
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = {
                    Text("Título")
                },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = {
                    Text("Descripción")
                },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = priority,
                onValueChange = { priority = it },
                label = {
                    Text("Prioridad")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    if (taskId != null) {
                        taskViewModel.updateTask(
                            id = taskId,
                            title = title,
                            description = description,
                            priority = priority
                        )
                    }

                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.HOME) {
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar cambios")
            }
        }
    }
}