package com.example.pianoapp.usecase.connection

import android.content.Context
import android.media.midi.MidiDeviceInfo
import android.media.midi.MidiManager
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.pianoapp.usecase.connection.data.MIDIConnectionStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class ConnectDeviceUseCase(
    private val keyboardSignalReceiver: KeyboardSignalReceiver,
    private val midiManager: MidiManager,
){

    fun getDevicesInfo(): List<MidiDeviceInfo> {
        return midiManager.devices.toList()
    }

    fun initMidiConnection(
        device: MidiDeviceInfo,
        onDeviceConnected: (MIDIConnectionStatus) -> Unit
    ){
        val outputPort = getOutputPort(device.ports.asList())
        if(outputPort == null){
            onDeviceConnected(MIDIConnectionStatus.DeviceWithNoOutputPorts)
            return
        }

        val deviceName = device.getName()

        try {
            val outputPortIndex = outputPort.portNumber
            midiManager.openDevice(
                device, {
                    Log.i("CONNECT_DEVICE_USE_CASE", "Inside")
                    try {
                        it.openOutputPort(outputPortIndex).connect(keyboardSignalReceiver)
                        Log.i("CONNECT_DEVICE_USE_CASE", "Piano $deviceName connected")
                        onDeviceConnected(MIDIConnectionStatus.Connected(it.info))
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

    private fun getOutputPort(ports: List<MidiDeviceInfo.PortInfo>): MidiDeviceInfo.PortInfo? {
        return ports.find { it.type == MidiDeviceInfo.PortInfo.TYPE_OUTPUT }
    }

}
