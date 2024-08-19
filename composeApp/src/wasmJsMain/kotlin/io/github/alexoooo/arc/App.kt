package io.github.alexoooo.arc

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier


@Composable
fun App() {
    MaterialTheme {
        ScreenTabs()
    }
}


//---------------------------------------------------------------------------------------------------------------------
@Composable
fun ScreenTabs() {
    var tabIndex by remember { mutableStateOf(0) }

    val tabs = listOf("Solve", "Generate")

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> SolveScreen()
            1 -> GenerateScreen()
        }
    }
}
