import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource

import ao_arc.composeapp.generated.resources.Res
import ao_arc.composeapp.generated.resources.compose_multiplatform
import io.github.alexoooo.arc.ArcColor
import io.github.alexoooo.arc.ArcGrid

@Composable
fun App() {
    MaterialTheme {
//        var showContent by remember { mutableStateOf(false) }

        var text by remember { mutableStateOf("[[0,1],[1,0]]") }
        var grid: ArcGrid? by remember { mutableStateOf(ArcGrid.ofJson(text)) }
        var gridError: String? by remember { mutableStateOf(null) }

        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
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

            Text("Grid: $grid")
            Text("Error: $gridError")

//            Button(onClick = { showContent = !showContent }) {
//                Text("Foo")
//            }
//            AnimatedVisibility(showContent) {
////                val greeting = remember { Greeting().greet() }
//                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//                    for (i in 0 ..< 10) {
//                        Text(ArcColor.ofIndex(i).name)
//                    }
//
////                    Image(painterResource(Res.drawable.compose_multiplatform), null)
////                    Text("Compose: $greeting")
//                }
//            }
        }
    }
}