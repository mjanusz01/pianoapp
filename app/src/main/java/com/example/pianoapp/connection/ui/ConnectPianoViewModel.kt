package com.example.pianoapp.connection.ui

import android.media.midi.MidiDeviceInfo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pianoapp.connection.usecase.connectdevice.ConnectDeviceUseCase
import com.example.pianoapp.connection.usecase.connectdevice.MIDIConnectionStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ConnectPianoViewModel(
    private val connectDeviceUseCase: ConnectDeviceUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(ConnectPianoViewState())
    val uiState: StateFlow<ConnectPianoViewState> = _uiState

    init {
        loadDeviceInfo()
    }

    fun onDeviceChoice(): (Device) -> Unit = {
        viewModelScope.launch {
            it.midiDeviceInfo?.let { midiDeviceInfo ->
                connectDeviceUseCase.initMidiConnection(
                    midiDeviceInfo
                ) { onConnectionFinished(it) }
            }
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
        val newDevices = connectDeviceUseCase.getDevicesInfo().toDevice()
        _uiState.update {
            it.copy(
                devices = newDevices
            )
        }
    }

    private fun MIDIConnectionStatus.toDialogState(): ConnectionDialogState = when (this) {
        is MIDIConnectionStatus.Connected -> ConnectionDialogState.DeviceConnectedDialog(this.midiDeviceInfo)
        else -> ConnectionDialogState.ErrorDialog(this)
    }
}

data class ConnectPianoViewState(
    val devices: List<Device> = emptyList(),
    val USBConnectionDialogStatus: ConnectionDialogState = ConnectionDialogState.DialogNotVisible,
)

sealed class ConnectionDialogState {
    data object DialogNotVisible : ConnectionDialogState()
    data class DeviceConnectedDialog(val midiDeviceInfo: MidiDeviceInfo) : ConnectionDialogState()
    data class ErrorDialog(val midiConnectionStatus: MIDIConnectionStatus) : ConnectionDialogState()
}