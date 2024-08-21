package io.github.alexoooo.arc

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.alexoooo.arc.generate.GeometryArcGenerator
import io.github.alexoooo.arc.generate.RandomArcGenerator
import kotlin.random.Random


private val scrollIndicatorWidth = 8.dp
private val scrollIndicatorHeight = 20.dp
private val editorWidth = 1000.dp
private val editorHeight = 400.dp

@Composable
fun GenerateScreen() {
    val task by remember {
//        mutableStateOf(RandomArcGenerator(42).generate())
//        mutableStateOf(GeometryArcGenerator(Random(42)).generate())
        mutableStateOf(GeometryArcGenerator(Random).generate())
    }

    val verticalScrollState = rememberScrollState()
    val horizontalScrollState = rememberScrollState()

    Box(
        Modifier.fillMaxSize()
    ) {
        Column(
            Modifier
                .width(editorWidth)
                .height(editorHeight)
                .verticalScroll(verticalScrollState)
                .horizontalScroll(horizontalScrollState)
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = task.toJson(),
                onValueChange = {}
            )
        }

        VerticalScrollIndicator(verticalScrollState)
        HorizontalScrollIndicator(horizontalScrollState)
    }
}


@Composable
fun VerticalScrollIndicator(scrollState: ScrollState) {
    val scrollFraction = scrollState.value.toFloat() / scrollState.maxValue.toFloat()

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(scrollIndicatorWidth)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val canvasHeight = size.height
            val indicatorOffset = scrollFraction * (canvasHeight - scrollIndicatorHeight.toPx())

            drawRect(
                color = Color.Gray,
                topLeft = Offset(x = 0f, y = indicatorOffset),
                size = Size(width = size.width, height = scrollIndicatorHeight.toPx())
            )
        }
    }
}


@Composable
fun HorizontalScrollIndicator(scrollState: ScrollState) {
    val scrollFraction = scrollState.value.toFloat() / scrollState.maxValue.toFloat()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(scrollIndicatorWidth)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val canvasHeight = size.width
            val indicatorOffset = scrollFraction * (canvasHeight - scrollIndicatorHeight.toPx())

            drawRect(
                color = Color.Gray,
                topLeft = Offset(x = indicatorOffset, y = 0f),
                size = Size(width =  scrollIndicatorHeight.toPx(), height = size.height)
            )
        }
    }
}
