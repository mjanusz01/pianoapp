package com.example.pianoapp.connection.ui

import android.media.midi.MidiDeviceInfo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pianoapp.usecase.connection.ConnectDeviceUseCase
import com.example.pianoapp.usecase.connection.data.MIDIConnectionStatus
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

    fun onDeviceChoice(): (MidiDeviceInfo) -> Unit = {
        viewModelScope.launch {
            connectDeviceUseCase.initMidiConnection(it) { onConnectionFinished(it) }
        }

    }

    private fun onConnectionFinished(midiConnectionStatus: MIDIConnectionStatus){
        _uiState.update {
            it.copy(
                USBConnectionDialogStatus = midiConnectionStatus.toDialogState(),
                pianoConnectionState = midiConnectionStatus.toPianoConnectionState()
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
        val devices = connectDeviceUseCase.getDevicesInfo()
        _uiState.update {
            it.copy(
                USBDevicesList = devices
            )
        }
    }

    private fun MIDIConnectionStatus.toDialogState(): ConnectionDialogState = when (this) {
        is MIDIConnectionStatus.Connected -> ConnectionDialogState.DeviceConnectedDialog(this.midiDeviceInfo)
        else -> ConnectionDialogState.ErrorDialog(this)
    }

    private fun MIDIConnectionStatus.toPianoConnectionState(): PianoConnectionState = when(this) {
        is MIDIConnectionStatus.Connected -> PianoConnectionState.PianoConnected(this.midiDeviceInfo)
        else -> PianoConnectionState.PianoNotConnected
    }
}

data class ConnectPianoViewState(
    val USBDevicesList: List<MidiDeviceInfo> = emptyList(),
    val USBConnectionDialogStatus: ConnectionDialogState = ConnectionDialogState.DialogNotVisible,
    val pianoConnectionState: PianoConnectionState = PianoConnectionState.PianoNotConnected
)

sealed class ConnectionDialogState {
    data object DialogNotVisible: ConnectionDialogState()
    data class DeviceConnectedDialog(val midiDeviceInfo: MidiDeviceInfo): ConnectionDialogState()
    data class ErrorDialog(val midiConnectionStatus: MIDIConnectionStatus): ConnectionDialogState()
}

sealed class PianoConnectionState {
    data class PianoConnected(val midiDeviceInfo: MidiDeviceInfo): PianoConnectionState()
    data object PianoNotConnected: PianoConnectionState()
}