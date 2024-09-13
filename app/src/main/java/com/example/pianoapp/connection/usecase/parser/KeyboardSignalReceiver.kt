package com.example.pianoapp.connection.usecase.parser

import android.media.midi.MidiReceiver
import android.util.Log

class KeyboardSignalReceiver(
    private val parseMidiUseCase: ParseMIDIUseCase
) : MidiReceiver() {

    override fun onSend(msg: ByteArray?, offset: Int, count: Int, timestamp: Long) {
        if(msg != null){
            Log.i("KEYBOARD_SIGNAL_RECEIVER", "Received note ${msg.contentToString()}")
            parseMidiUseCase.onNoteReceived(msg)
        } else{
            Log.e("KEYBOARD_SIGNAL_RECEIVER", "Empty note received")
        }
    }
}

