package com.example.pianoapp

import android.media.midi.MidiDeviceInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pianoapp.connection.ui.ConnectPianoScreen
import com.example.pianoapp.connection.ui.ConnectPianoViewModel
import com.example.pianoapp.ui.theme.PianoAppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: ConnectPianoViewModel by viewModel()

        setContent {
            val uiState by viewModel.uiState.collectAsState()

            PianoAppTheme {
                ConnectPianoScreen(
                    connectionDialogState = uiState.USBConnectionDialogStatus,
                    onDeviceChoice = viewModel.onDeviceChoice(),
                    onDialogDismiss = { viewModel.onDialogDismissed() },
                    USBDevicesList = uiState.USBDevicesList,
                )
            }
        }
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