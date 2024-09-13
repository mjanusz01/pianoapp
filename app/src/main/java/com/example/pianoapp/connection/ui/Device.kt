package com.example.pianoapp.connection.ui

import android.media.midi.MidiDeviceInfo
import com.example.pianoapp.connection.usecase.getName

data class Device(
    val midiDeviceInfo: MidiDeviceInfo? = null,
    val isConnected: Boolean = false,
    val name: String? = null
)

fun List<Device>.getConnectedDevice() = find { it.isConnected }

fun List<MidiDeviceInfo>.toDevice() = this.map{ Device(it, false, it.getName()) }