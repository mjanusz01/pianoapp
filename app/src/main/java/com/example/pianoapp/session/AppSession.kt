package com.example.pianoapp.session

import android.media.midi.MidiDevice
import android.media.midi.MidiDeviceInfo
import com.example.pianoapp.connection.ui.Device

class AppSession {

    private var deviceConnectionState: DeviceConnectionState =
        DeviceConnectionState.DeviceNotConnected

    fun isThisDeviceConnected(device: Device) =
        deviceConnectionState is DeviceConnectionState.DeviceConnected && device.midiDeviceInfo.id == (deviceConnectionState as DeviceConnectionState.DeviceConnected).midiDevice.info.id

    fun setConnectedDevice(midiDevice: MidiDevice) {
        deviceConnectionState = DeviceConnectionState.DeviceConnected(midiDevice)
    }

    fun getDeviceConnectionState() = deviceConnectionState

    fun clearConnectedDevice() {
        deviceConnectionState = DeviceConnectionState.DeviceNotConnected
    }
}

sealed class DeviceConnectionState {
    data class DeviceConnected(val midiDevice: MidiDevice) : DeviceConnectionState()
    data object DeviceNotConnected : DeviceConnectionState()
}
