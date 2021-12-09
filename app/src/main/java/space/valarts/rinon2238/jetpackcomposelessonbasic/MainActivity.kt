package space.valarts.rinon2238.jetpackcomposelessonbasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
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
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    if (shouldShowOnboarding) {
        OnboardingScreen {
            shouldShowOnboarding = false
        }
    } else {
        Greetings()
    }
}

@Composable
fun Greetings() {
    val list: List<String> = List(1000) { "soy source. id: $it" }

    Surface(color = MaterialTheme.colors.background) {
        LazyColumn(modifier = Modifier.padding(bottom = 4.dp)) {
            items(items = list) { name ->
                Greeting(name = name)
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val extraBottomPadding by animateDpAsState(
        targetValue = if (expanded) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

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
                .padding(bottom = extraBottomPadding.coerceAtLeast(0.dp))
            ) {
                Text(text = "This is")
                Text(text = "$name!")
            }

            OutlinedButton(
                onClick = { expanded = !expanded }
            ) {
                Text(text = if (expanded) "show less" else "show more")
            }
        }
    }
}

@Composable
fun OnboardingScreen(onContinueClick: () -> Unit) {
    Surface {
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("Welcome to the Basics Codelab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClick
            ) {
                Text("Continue")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    JetpackComposeLessonBasicTheme {
        Greetings()
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    JetpackComposeLessonBasicTheme {
        OnboardingScreen {}
    }
}