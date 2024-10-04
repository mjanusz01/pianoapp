package com.example.pianoapp.connection.usecase.connectdevice

import android.media.midi.MidiDeviceInfo
import android.media.midi.MidiManager
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.pianoapp.connection.ui.Device
import com.example.pianoapp.connection.usecase.parser.KeyboardSignalReceiver
import com.example.pianoapp.connection.usecase.getName
import com.example.pianoapp.session.AppSession

class ConnectDeviceUseCase(
    private val keyboardSignalReceiver: KeyboardSignalReceiver,
    private val midiManager: MidiManager,
    private val appSession: AppSession
){

    fun getDevicesInfo(): List<MidiDeviceInfo> {
        return midiManager.devices.toList()
    }

    fun connectMidiDevice(
        device: Device,
        onDeviceConnected: (MIDIConnectionStatus) -> Unit
    ){
        val outputPort = device.midiDeviceInfo.ports?.let { getOutputPort(it.asList()) }
        if(outputPort == null){
            onDeviceConnected(MIDIConnectionStatus.DeviceWithNoOutputPorts)
            return
        }

        val deviceName = device.midiDeviceInfo.getName()

        try {
            val outputPortIndex = outputPort.portNumber
            midiManager.openDevice(
                device.midiDeviceInfo, {
                    Log.i("CONNECT_DEVICE_USE_CASE", "Inside")
                    try {
                        it.openOutputPort(outputPortIndex).connect(keyboardSignalReceiver)
                        Log.i("CONNECT_DEVICE_USE_CASE", "Piano $deviceName connected")
                        appSession.setConnectedDevice(it)
                        onDeviceConnected(MIDIConnectionStatus.Connected(it.info.getName() ?: ""))
                    } catch (exception: Exception) {
                        Log.e("CONNECT_DEVICE_USE_CASE", "Piano $deviceName has no output ports or can't connect with selected output port!")
                        onDeviceConnected(MIDIConnectionStatus.CantConnectWithThisOutputPort)
                    }
                },
                Handler(Looper.getMainLooper())
            )
        } catch (e: Exception) {
            Log.e("CONNECT_DEVICE_USE_CASE", "Exception while connecting to the piano. $e")
            onDeviceConnected(MIDIConnectionStatus.CantConnectWithThisDevice)
        }
    }

    fun disconnectMidiDevice(){
        appSession.clearConnectedDevice()
    }

    private fun getOutputPort(ports: List<MidiDeviceInfo.PortInfo>): MidiDeviceInfo.PortInfo? {
        return ports.find { it.type == MidiDeviceInfo.PortInfo.TYPE_OUTPUT }
    }
}

sealed class MIDIConnectionStatus{
    data class Connected(val deviceName: String): MIDIConnectionStatus()
    data object CantConnectWithThisDevice: MIDIConnectionStatus()
    data object DeviceWithNoOutputPorts: MIDIConnectionStatus()
    data object CantConnectWithThisOutputPort: MIDIConnectionStatus()
}