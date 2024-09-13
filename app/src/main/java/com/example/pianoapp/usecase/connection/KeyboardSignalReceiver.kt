package com.example.pianoapp.usecase.connection

import android.media.midi.MidiReceiver
import android.util.Log

class KeyboardSignalReceiver(
    private val midiParser: MIDIParser
) : MidiReceiver() {

    override fun onSend(msg: ByteArray?, offset: Int, count: Int, timestamp: Long) {
        if(msg != null){
            Log.i("KEYBOARD_SIGNAL_RECEIVER", "Received note ${msg.contentToString()}")
            midiParser.onNoteReceived(msg)
        } else{
            Log.e("KEYBOARD_SIGNAL_RECEIVER", "Empty note received")
        }
    }
}

