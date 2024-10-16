package com.example.pianoapp.connection.ui

import android.media.midi.MidiDeviceInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.pianoapp.R
import com.example.pianoapp.ui.theme.PianoAppTheme
import com.example.pianoapp.usecase.connection.data.MIDIConnectionStatus
import com.example.pianoapp.usecase.connection.getName

@Composable
fun ConnectPianoScreen(
    connectionDialogState: ConnectionDialogState,
    onDialogDismiss: () -> Unit,
    onDeviceChoice: (MidiDeviceInfo) -> Unit,
    pianoConnectionState: PianoConnectionState,
    USBDevicesList: List<MidiDeviceInfo>
) {
    ConnectPianoScreenContent(
        onDeviceChoice = onDeviceChoice,
        USBDevicesList = USBDevicesList,
        pianoConnectionState = pianoConnectionState
    )
    Dialogs(
        connectionDialogState = connectionDialogState,
        onDismiss = onDialogDismiss
    )
}

@Composable
fun ConnectedPianoBanner(pianoName: String) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(Color(0xFF8c633a))
            .padding(24.dp)
    ) {
        Text(
            text = "Connected with: ",
            color = Color(0xFFbdbdbd),
            fontFamily = FontFamily(
                Font(R.font.poppins_regular)
            ),
            fontSize = 18.sp,
        )
        Text(
            text = pianoName,
            color = Color(0xFFbdbdbd),
            fontFamily = FontFamily(
                Font(R.font.poppins_bold)
            ),
            fontSize = 18.sp,
        )
    }
}

@Composable
fun Dialogs(connectionDialogState: ConnectionDialogState, onDismiss: () -> Unit) {
    when (connectionDialogState) {
        ConnectionDialogState.DialogNotVisible -> Unit
        else -> DeviceConnectionDialog(
            connectionDialogState,
            onDismiss
        )
    }

}

@Composable
fun ConnectPianoScreenContent(
    onDeviceChoice: (MidiDeviceInfo) -> Unit,
    USBDevicesList: List<MidiDeviceInfo>,
    pianoConnectionState: PianoConnectionState
) {
    Column(Modifier.fillMaxSize()) {
        if (pianoConnectionState is PianoConnectionState.PianoConnected) {
            ConnectedPianoBanner(pianoConnectionState.midiDeviceInfo.getName() ?: "")
        }
        Column(
            Modifier
                .background(Color(0xFF1a1a1a))
                .padding(24.dp)
        ) {
            Text(
                text = "Connect your device",
                color = Color(0xFFededed),
                fontFamily = FontFamily(
                    Font(R.font.poppins_medium)
                ),
                fontSize = 32.sp,
                lineHeight = 30.sp,
                modifier = Modifier.padding(start = 15.dp, end = 15.dp)
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = "Choose an instrument from available sources below:",
                color = Color(0xFFbdbdbd),
                fontFamily = FontFamily(
                    Font(R.font.poppins_light)
                ),
                fontSize = 18.sp,
                lineHeight = 17.sp,
                modifier = Modifier.padding(start = 15.dp, end = 15.dp)
            )
        }
        Column(
            Modifier
                .fillMaxSize()
                .background(Color(0xFF3d2c1d))
                .padding(start = 24.dp, end = 24.dp, bottom = 24.dp)
        ) {
            USBDevicesList.forEach {
                DeviceTile(
                    onDeviceChoice = { onDeviceChoice(it) },
                    deviceName = (it.getName()
                        ?.take(10) + " ..."),
                    iconResource = R.drawable.kawai_dark
                )
            }
            DeviceTile(
                onDeviceChoice = {},
                deviceName = "Kawai KDP 120",
                iconResource = R.drawable.kawai_dark
            )
        }
    }
}

@Composable
fun DeviceConnectionDialog(
    connectionDialogState: ConnectionDialogState,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.9F)
                .background(color = Color(0xFF1a1a1a), shape = RoundedCornerShape(15.dp))
                .padding(20.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    painter = painterResource(if (connectionDialogState is ConnectionDialogState.DeviceConnectedDialog) R.drawable.ok_circle_filled_svgrepo_com else R.drawable.no_round_svgrepo_com),
                    contentDescription = "",
                    tint = if (connectionDialogState is ConnectionDialogState.DeviceConnectedDialog) Color(
                        0xFFb4decf
                    ) else Color(0xFF962736),
                    modifier = Modifier.size(60.dp)
                )
            }
            Spacer(Modifier.height(20.dp))
            Text(
                text = connectionDialogState.toDialogText(),
                color = Color(0xFFbdbdbd),
                fontFamily = FontFamily(
                    Font(R.font.poppins_light)
                ),
                fontSize = 18.sp,
                lineHeight = 17.sp,
            )
            if(connectionDialogState is ConnectionDialogState.DeviceConnectedDialog)
            Text(
                text = connectionDialogState.midiDeviceInfo.getName() ?: "",
                color = Color(0xFFbdbdbd),
                fontFamily = FontFamily(
                    Font(R.font.poppins_medium)
                ),
                fontSize = 22.sp,
                lineHeight = 17.sp,
            )
        }
    }
}

@Composable
fun DeviceTile(
    onDeviceChoice: () -> Unit,
    deviceName: String,
    iconResource: Int,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(65.dp)
            .padding(top = 15.dp, bottom = 15.dp)
    ) {
        Image(
            painterResource(iconResource),
            contentDescription = null,
            modifier = Modifier.clip(RoundedCornerShape(7.dp))
        )
        Spacer(Modifier.width(15.dp))
        Column() {
            Spacer(Modifier.weight(1F))
            Row() {
                Text(
                    text = deviceName,
                    color = Color(0xFFbdbdbd),
                    fontFamily = FontFamily(
                        Font(R.font.poppins_light)
                    ),
                    fontSize = 18.sp,
                    lineHeight = 17.sp,
                )
                Spacer(Modifier.weight(1F))
                Text(
                    text = "Connect",
                    color = Color(0xFFd4d4d4),
                    fontFamily = FontFamily(
                        Font(R.font.poppins_semibold)
                    ),
                    fontSize = 18.sp,
                    lineHeight = 17.sp,
                    modifier = Modifier.clickable { onDeviceChoice() }
                )
                Spacer(Modifier.width(15.dp))
            }
            Spacer(Modifier.weight(1F))
        }
    }
    Divider(color = Color(0xFF665442))
}

@Composable
@Preview
fun ConnectPianoScreenPreview() {
    PianoAppTheme {
        ConnectPianoScreen(
            connectionDialogState = ConnectionDialogState.DialogNotVisible,
            onDeviceChoice = {},
            onDialogDismiss = {},
            USBDevicesList = emptyList(),
            pianoConnectionState = PianoConnectionState.PianoNotConnected
        )
    }
}

fun ConnectionDialogState.toDialogText(): String = when (this) {
    is ConnectionDialogState.DeviceConnectedDialog -> "You are succesfully connected with piano: "
    is ConnectionDialogState.ErrorDialog -> {
        when (this.midiConnectionStatus) {
            MIDIConnectionStatus.CantConnectWithThisDevice -> "Unfortunately an unknown error has occured. Please try again later or with another device."
            MIDIConnectionStatus.CantConnectWithThisOutputPort -> "Your device doesn't have valid output ports. Please try with another device."
            MIDIConnectionStatus.DeviceWithNoOutputPorts -> "Your device has no output ports. Please try with another device."
            else -> ""
        }
    }
    else -> ""
}

