package com.example.pianoapp.usecase.connection.data

import android.media.midi.MidiDeviceInfo

sealed class MIDIConnectionStatus{
    data class Connected(val midiDeviceInfo: MidiDeviceInfo): MIDIConnectionStatus()
    data object CantConnectWithThisDevice: MIDIConnectionStatus()
    data object DeviceWithNoOutputPorts: MIDIConnectionStatus()
    data object CantConnectWithThisOutputPort: MIDIConnectionStatus()
}