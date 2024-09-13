package com.example.pianoapp.usecase.connection

import android.media.midi.MidiDeviceInfo

fun MidiDeviceInfo.getName() = this.properties.getString(MidiDeviceInfo.PROPERTY_NAME)