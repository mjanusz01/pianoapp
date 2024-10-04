package com.example.pianoapp.connection.ui

import android.media.midi.MidiDevice
import android.media.midi.MidiDeviceInfo
import com.example.pianoapp.connection.usecase.getName
import com.example.pianoapp.session.DeviceConnectionState

data class Device(
    val midiDeviceInfo: MidiDeviceInfo,
    val isConnected: Boolean = false,
    val name: String? = null
)

fun List<Device>.getConnectedDevice() = find { it.isConnected }

fun List<MidiDeviceInfo>.toDevice() = this.map{ Device(it, false, it.getName()) }