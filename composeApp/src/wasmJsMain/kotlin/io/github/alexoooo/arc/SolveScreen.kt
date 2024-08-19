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
import io.github.alexoooo.arc.model.ArcGrid


@Composable
fun SolveScreen() {
    var text by remember { mutableStateOf("[[0,1],[1,0]]") }
    var grid: ArcGrid? by remember { mutableStateOf(ArcGrid.ofJson(text)) }
    var gridError: String? by remember { mutableStateOf(null) }

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
                    grid = ArcGrid.ofJson(it)
                    gridError = null
                }
                catch (e: Exception) {
                    grid = null
                    gridError = e::class.simpleName + ": " + e.message
                }
            }
        )

        if (gridError != null) {
            Text("Error: $gridError", color = Color.Red)
        }
        else {
            check(grid != null)

            val dimensions = grid!!.dimensions
            Text("Dimensions: $dimensions")

            val cells = grid!!.cells
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
    }
}
