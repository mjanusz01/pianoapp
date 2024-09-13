package com.example.pianoapp.connection.usecase.parser

class ParseMIDIUseCase {

    fun onNoteReceived(msg: ByteArray) {
        val isKeyPressedOrReleased = isKeyPressedOrReleased(msg[1])
        val noteFromByteData = getNoteFromByteData(msg[2])
        val keyPressPower = getKeyPressPower(msg[3])

        val keyPressData =  KeyPressData(
            isKeyPressedOrReleased,
            noteFromByteData,
            keyPressPower
        )

        println(keyPressData)
    }

    private fun isKeyPressedOrReleased(data: Byte): KeyPressInfoState {
        return if (data.toInt() == -112) KeyPressInfoState.KEY_PRESSED
            else if (data.toInt() == -128) KeyPressInfoState.KEY_RELEASED
            else throw MIDIParseException.KeyPressedOrReleasedException(
            "Int value of byte should be -112 or -128, it was ${data.toInt()}"
        )
    }

    private fun getNoteFromByteData(data: Byte): Note {
        return if (data.toInt() in 36 .. 96) Note.entries[data.toInt() - 36]
            else throw MIDIParseException.ParseNoteIndexException(
            "Int value of byte should be between 36 and 96, it was ${data.toInt()}"
        )
    }

    private fun getKeyPressPower(data: Byte) : Int {
        return if (data.toInt() >= 0) data.toInt()
        else throw MIDIParseException.KeyPressPowerException(
            "Power press of MIDI is ${data.toInt()}, should be >= 0"
        )
    }
}

sealed class MIDIParseException : Exception(){
    data class KeyPressedOrReleasedException(override val message: String): MIDIParseException()
    data class ParseNoteIndexException(override val message: String): MIDIParseException()
    data class KeyPressPowerException(override val message: String): MIDIParseException()
}