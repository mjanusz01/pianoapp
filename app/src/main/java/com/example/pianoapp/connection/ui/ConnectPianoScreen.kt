package com.example.pianoapp.connection.ui

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.pianoapp.connection.usecase.connectdevice.MIDIConnectionStatus
import com.example.pianoapp.connection.usecase.getName
import com.example.pianoapp.ui.theme.PianoAppTheme

@Composable
fun ConnectPianoScreen(
    connectionDialogState: ConnectionDialogState,
    onDialogDismiss: () -> Unit,
    onDeviceChoice: (Device) -> Unit,
    devices: List<Device>
) {
    ConnectPianoScreenContent(
        onDeviceChoice = onDeviceChoice,
        devices = devices
    )
    Dialogs(
        connectionDialogState = connectionDialogState,
        onDismiss = onDialogDismiss
    )
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
    onDeviceChoice: (Device) -> Unit,
    devices: List<Device>
) {
    Column(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .background(Color(0xFF050505))
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
                color = Color(0xFFe1dae8),
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
                .background(Color(0xFF141414))
                .padding(start = 24.dp, end = 24.dp, bottom = 24.dp)
        ) {
            Spacer(Modifier.height(15.dp))
            LazyColumn() {
                items(devices) {
                    DeviceTile(
                        onDeviceChoice = { onDeviceChoice(it) },
                        deviceName = (it.name ?: "Unknown name"),
                        iconResource = R.drawable.kawai_dark,
                        it.isConnected
                    )
                    Spacer(Modifier.height(15.dp))
                }
            }
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
            if (connectionDialogState is ConnectionDialogState.DeviceConnectedDialog)
                Text(
                    text = connectionDialogState.deviceName,
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
    isDeviceConnected: Boolean,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(
                color = if (isDeviceConnected) Color(0xFF83dea7) else Color(0xFF3f3a45),
                shape = RoundedCornerShape(20.dp)
            )
            .height(65.dp)
            .padding(15.dp)
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
                    text = deviceName.take(10),
                    color = if (isDeviceConnected) Color(0xFF212121) else Color(0xFFffffff),
                    fontFamily = FontFamily(
                        Font(R.font.poppins_regular)
                    ),
                    fontSize = 18.sp,
                    lineHeight = 17.sp,
                )
                Spacer(Modifier.weight(1F))
                Text(
                    text = if (isDeviceConnected) "Disconnect" else "Connect",
                    color = if (isDeviceConnected) Color(0xFFa13543) else Color(0xFF0c993e),
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
}

@Composable
@Preview
fun ConnectPianoScreenPreview() {
    PianoAppTheme {
        ConnectPianoScreen(
            connectionDialogState = ConnectionDialogState.DialogNotVisible,
            onDeviceChoice = {},
            onDialogDismiss = {},
            devices = emptyList()
        )
    }
}

@Composable
@Preview
fun DeviceTileConnectedPreview(){
    PianoAppTheme {
        DeviceTile(
            onDeviceChoice = {},
            deviceName = "Kawai KDP 120",
            iconResource = R.drawable.kawai_dark,
            isDeviceConnected = true
        )
    }
}

@Composable
@Preview
fun DeviceTileNotPreview(){
    PianoAppTheme {
        DeviceTile(
            onDeviceChoice = {},
            deviceName = "Kawai KDP 120",
            iconResource = R.drawable.kawai_dark,
            isDeviceConnected = false
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

