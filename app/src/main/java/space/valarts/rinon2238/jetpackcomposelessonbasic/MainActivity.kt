package space.valarts.rinon2238.jetpackcomposelessonbasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import space.valarts.rinon2238.jetpackcomposelessonbasic.ui.theme.JetpackComposeLessonBasicTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeLessonBasicTheme {
                // A surface container using the 'background' color from the theme
                MyApp() // Cleaned up...
            }
        }
    }
}

@Composable
private fun MyApp() {
    val list = listOf("soy source", "oyster source")

    Surface(color = MaterialTheme.colors.background) {
        Column {
            list.forEach {
                Greeting(name = it)
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    val expanded = remember { mutableStateOf(false) }
    val extraBottomPadding = if (expanded.value) 48.dp else 0.dp

    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
        ) {
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = extraBottomPadding)
            ) {
                Text(text = "This is")
                Text(text = "$name!")
            }

            OutlinedButton(
                onClick = { expanded.value = !expanded.value }
            ) {
                Text(text = if (expanded.value) "show less" else "show more")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    JetpackComposeLessonBasicTheme {
        MyApp()
    }
}