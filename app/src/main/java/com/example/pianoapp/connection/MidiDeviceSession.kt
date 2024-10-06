package com.example.pianoapp.connection

import android.media.midi.MidiDevice
import android.media.midi.MidiOutputPort
import com.example.pianoapp.connection.ui.Device

class MidiDeviceSession {

    private var deviceConnectionState: DeviceConnectionState =
        DeviceConnectionState.DeviceNotConnected

    fun isThisDeviceConnected(device: Device) =
        deviceConnectionState is DeviceConnectionState.DeviceConnected && device.midiDeviceInfo.id == (deviceConnectionState as DeviceConnectionState.DeviceConnected).midiDevice.info.id

    fun setConnectedDevice(midiDevice: MidiDevice, outputPort: MidiOutputPort) {
        deviceConnectionState = DeviceConnectionState.DeviceConnected(midiDevice, outputPort)
    }

    fun getDeviceConnectionState() = deviceConnectionState

    fun clearConnectedDevice() {
        if(deviceConnectionState is DeviceConnectionState.DeviceConnected){
            (deviceConnectionState as DeviceConnectionState.DeviceConnected).outputPort.close()
            (deviceConnectionState as DeviceConnectionState.DeviceConnected).midiDevice.close()
        }
        deviceConnectionState = DeviceConnectionState.DeviceNotConnected
    }
}

sealed class DeviceConnectionState {
    data class DeviceConnected(val midiDevice: MidiDevice, val outputPort: MidiOutputPort) : DeviceConnectionState()
    data object DeviceNotConnected : DeviceConnectionState()
}
