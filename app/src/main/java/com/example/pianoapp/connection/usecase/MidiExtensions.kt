package com.example.pianoapp.connection.usecase

import android.media.midi.MidiDeviceInfo

fun MidiDeviceInfo.getName() = this.properties.getString(MidiDeviceInfo.PROPERTY_NAME)

fun List<MidiDeviceInfo>.toMidiDeviceDisplayData() = this.map {}