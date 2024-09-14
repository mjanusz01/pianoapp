package com.example.pianoapp.connection.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pianoapp.connection.usecase.connectdevice.ConnectDeviceUseCase
import com.example.pianoapp.connection.usecase.connectdevice.MIDIConnectionStatus
import com.example.pianoapp.session.AppSession
import com.example.pianoapp.session.DeviceConnectionState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ConnectPianoViewModel(
    private val connectDeviceUseCase: ConnectDeviceUseCase,
    private val appSession: AppSession
) : ViewModel() {
    private val _uiState = MutableStateFlow(ConnectPianoViewState())
    val uiState: StateFlow<ConnectPianoViewState> = _uiState

    init {
        loadDeviceInfo()
    }

    fun onDeviceChoice(): (Device) -> Unit = {
        viewModelScope.launch {
            connectDeviceUseCase.initMidiConnection(it) { onConnectionFinished(it) }
        }
    }

    private fun onConnectionFinished(midiConnectionStatus: MIDIConnectionStatus) {
        _uiState.update {
            it.copy(
                USBConnectionDialogStatus = midiConnectionStatus.toDialogState(),
            )
        }
    }

    fun onDialogDismissed() {
        _uiState.update {
            it.copy(
                USBConnectionDialogStatus = ConnectionDialogState.DialogNotVisible
            )
        }
        loadDeviceInfo()
    }

    private fun loadDeviceInfo() {
        val deviceConnectionState = appSession.getDeviceConnectionState()
        val updatedDevices =
            connectDeviceUseCase.getDevicesInfo().toDevice()
                .map {
                    if (deviceConnectionState is DeviceConnectionState.DeviceConnected
                        && it.midiDeviceInfo.id == deviceConnectionState.device.midiDeviceInfo.id
                    )
                        deviceConnectionState.device.toConnectedDevice()
                    else
                        it
                }
        _uiState.update {
            it.copy(
                devices = updatedDevices
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
    val USBConnectionDialogStatus: ConnectionDialogState = ConnectionDialogState.DialogNotVisible,
    val isLoading: Boolean = false
)

sealed class ConnectionDialogState {
    data object DialogNotVisible : ConnectionDialogState()
    data class DeviceConnectedDialog(val deviceName: String) : ConnectionDialogState()
    data class ErrorDialog(val midiConnectionStatus: MIDIConnectionStatus) : ConnectionDialogState()
}