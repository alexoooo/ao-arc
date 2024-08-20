package io.github.alexoooo.arc

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import io.github.alexoooo.arc.model.ArcExample
import io.github.alexoooo.arc.model.ArcGrid
import io.github.alexoooo.arc.model.ArcTask


private const val sampleTaskJson = """{
  "train": [
    {"input": [[1, 0], [0, 0]], "output": [[1, 1], [1, 1]]},
    {"input": [[0, 0], [4, 0]], "output": [[4, 4], [4, 4]]},
    {"input": [[0, 0], [6, 0]], "output": [[6, 6], [6, 6]]}
  ],
  "test": [
    {"input": [[0, 0], [0, 8]], "output": [[8, 8], [8, 8]]}
  ]
}"""


@Composable
fun SolveScreen() {
    var text by remember { mutableStateOf(sampleTaskJson) }
    var task: ArcTask? by remember { mutableStateOf(ArcTask.ofJson(text)) }
    var error: String? by remember { mutableStateOf(null) }

    Column(
        Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = text,
            onValueChange = {
                text = it

                try {
                    task = ArcTask.ofJson(it)
                    error = null
                }
                catch (e: Exception) {
                    task = null
                    error = e::class.simpleName + ": " + e.message
                }
            }
        )

        if (error != null) {
            Text("Error: $error", color = Color.Red)
        }
        else {
            val currentTask = task
            check(currentTask != null)
            ArcTaskView(currentTask)
        }
    }
}


@Composable
fun ArcTaskView(arcTask: ArcTask) {
    Text("Training examples: ${arcTask.train.size}")
    for (trainExample in arcTask.train) {
        ArcExampleView(trainExample)
    }

    Text("Test examples: ${arcTask.test.size}")
    for (testExample in arcTask.test) {
        ArcExampleView(testExample)
    }
}


@Composable
fun ArcExampleView(arcExample: ArcExample) {
    Text("-------------------------------------------------------")
    Text("Input:")
    ArcGridView(arcExample.input)

    Text("Output:")
    ArcGridView(arcExample.output)
}


@Composable
fun ArcGridView(arcGrid: ArcGrid) {
    val dimensions = arcGrid.dimensions
    Text("Dimensions: $dimensions")

    val cells = arcGrid.cells
    for (row in cells) {
        Row {
            for (cell in row) {
                Box(modifier = Modifier
                    .size(Dp(25f))
                    .background(Color(cell.argbColorCode))
                ) {
                    Text("${cell.index}", color = Color.White)
                }
            }
        }
    }
}