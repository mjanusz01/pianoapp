package com.example.pianoapp.usecase.connection.data

sealed class MIDIParseException : Exception(){
    data class KeyPressedOrReleasedException(override val message: String): MIDIParseException()
    data class ParseNoteIndexException(override val message: String): MIDIParseException()
    data class KeyPressPowerException(override val message: String): MIDIParseException()
}