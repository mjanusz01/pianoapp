package com.example.pianoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pianoapp.connection.ui.CONNECT_SCREEN_ROUTE
import com.example.pianoapp.connection.ui.ConnectPianoScreen
import com.example.pianoapp.dashboard.ui.DASHBOARD_SCREEN_ROUTE
import com.example.pianoapp.dashboard.ui.DashboardScreen
import com.example.pianoapp.keyboard.ui.KeyboardComponent
import com.example.pianoapp.play.PLAY_SCREEN_ROUTE
import com.example.pianoapp.play.PlayScreen
import com.example.pianoapp.ui.theme.PianoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PianoAppTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "dashboard") {
        composable(DASHBOARD_SCREEN_ROUTE) { DashboardScreen(navController) }
        composable(CONNECT_SCREEN_ROUTE) { ConnectPianoScreen(navController) }
        composable(PLAY_SCREEN_ROUTE) { PlayScreen() }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PianoAppTheme {
        Greeting("Android")
    }
}