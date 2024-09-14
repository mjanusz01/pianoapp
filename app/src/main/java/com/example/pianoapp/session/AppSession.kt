package com.example.pianoapp.session

import com.example.pianoapp.connection.ui.Device

class AppSession{

    private var deviceConnectionState: DeviceConnectionState = DeviceConnectionState.DeviceNotConnected

    fun isDeviceConnected() : Boolean = deviceConnectionState is DeviceConnectionState.DeviceConnected

    fun setConnectedDevice(device: Device){
        deviceConnectionState = DeviceConnectionState.DeviceConnected(device)
    }

    fun getDeviceConnectionState() = deviceConnectionState

    fun clearConnectedDevice(){
        deviceConnectionState = DeviceConnectionState.DeviceNotConnected
    }

}

sealed class DeviceConnectionState{
    data class DeviceConnected(val device: Device) : DeviceConnectionState()
    data object DeviceNotConnected : DeviceConnectionState()
}
