package com.example.pianoapp.connection.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pianoapp.connection.usecase.connectdevice.ConnectDeviceUseCase
import com.example.pianoapp.connection.usecase.connectdevice.MIDIConnectionStatus
import com.example.pianoapp.connection.MidiDeviceSession
import com.example.pianoapp.connection.DeviceConnectionState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ConnectPianoViewModel(
    private val connectDeviceUseCase: ConnectDeviceUseCase,
    private val midiDeviceSession: MidiDeviceSession
) : ViewModel() {
    private val _uiState = MutableStateFlow(ConnectPianoViewState())
    val uiState: StateFlow<ConnectPianoViewState> = _uiState

    init {
        loadDeviceInfo()
    }

    fun onDeviceChoice(): (Device) -> Unit = { it ->
        if (midiDeviceSession.isThisDeviceConnected(it)) {
            disconnectDevice()
        } else {
            viewModelScope.launch {
                connectDeviceUseCase.connectMidiDevice(it) { onConnectionFinished(it) }
            }
        }
    }

    private fun disconnectDevice() {
        val updatedDevices = uiState.value.devices.map {
            Device(it.midiDeviceInfo, false, it.name)
        }
        onDevicesListChanged(updatedDevices)
        connectDeviceUseCase.disconnectMidiDevice()
        loadDeviceInfo()
    }

    private fun onConnectionFinished(midiConnectionStatus: MIDIConnectionStatus) {
        _uiState.update {
            it.copy(
                dialogStatus = midiConnectionStatus.toDialogState(),
            )
        }
        loadDeviceInfo()
    }

    fun onDialogDismissed() {
        _uiState.update {
            it.copy(
                dialogStatus = ConnectionDialogState.DialogNotVisible
            )
        }
    }

    fun loadDeviceInfo() {
        val deviceConnectionState = midiDeviceSession.getDeviceConnectionState()
        val updatedDevices =
            connectDeviceUseCase.getDevicesInfo().toDevice().map {
                Device(
                    it.midiDeviceInfo,
                    deviceConnectionState is DeviceConnectionState.DeviceConnected && it.midiDeviceInfo.id == deviceConnectionState.midiDevice.info.id,
                    it.name
                )
            }
        onDevicesListChanged(updatedDevices)
    }

    private fun onDevicesListChanged(newDevices: List<Device>) {
        _uiState.update {
            it.copy(
                devices = newDevices
            )
        }
    }

    private fun MIDIConnectionStatus.toDialogState(): ConnectionDialogState = when (this) {
        is MIDIConnectionStatus.Connected -> ConnectionDialogState.DeviceConnectedDialog(this.deviceName)
        else -> ConnectionDialogState.ErrorDialog(this)
    }
}

data class ConnectPianoViewState(
    val devices: List<Device> = emptyList(),
    val dialogStatus: ConnectionDialogState = ConnectionDialogState.DialogNotVisible,
    val isLoading: Boolean = false
)

sealed class ConnectionDialogState {
    data object DialogNotVisible : ConnectionDialogState()
    data class DeviceConnectedDialog(val deviceName: String) : ConnectionDialogState()
    data class ErrorDialog(val midiConnectionStatus: MIDIConnectionStatus) : ConnectionDialogState()
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