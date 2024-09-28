package com.example.pianoapp.connection.usecase.parser

val blackKeys = listOf(
    Note.CIS0, Note.DIS0, Note.FIS0, Note.GIS0, Note.B0,
    Note.CIS1, Note.DIS1, Note.FIS1, Note.GIS1, Note.B1,
    Note.CIS2, Note.DIS2, Note.FIS2, Note.GIS2, Note.B2,
    Note.CIS3, Note.DIS3, Note.FIS3, Note.GIS3, Note.B3,
    Note.CIS4, Note.DIS4, Note.FIS4, Note.GIS4, Note.B4,
)

val blackKeysWithMoreSpace = listOf(Note.DIS0, Note.B0, Note.DIS1, Note.B1, Note.DIS2, Note.B2, Note.DIS3, Note.B3, Note.DIS4, Note.B4, Note.DIS5, Note.B5)

val whiteKeysWithNoBlackKeyPrevious = listOf(Note.C0, Note.F0, Note.C1, Note.F1, Note.C2, Note.F2, Note.C3, Note.F3, Note.C4, Note.F4, Note.C5, Note.F5)

fun Note.isBlack() = blackKeys.contains(this)

fun Note.isWhite() = !this.isBlack()

fun Note.getPreviousOrFirstPossibleNote(): Note {
    val index = Note.entries.indexOf(this) - 1
    if(index < 0){
        return Note.C0
    } else{
        return Note.entries[index]
    }
}

fun Note.getNextOrLastPossibleNote() : Note {
    val index = Note.entries.indexOf(this) + 1
    if(index > Note.entries.size){
        return Note.C6
    } else{
        return Note.entries[index]
    }
}

fun Note.hasNextKeyMoreSpace(): Boolean = blackKeysWithMoreSpace.contains(this)

fun Note.hasNoPreviousBlackKey(): Boolean = whiteKeysWithNoBlackKeyPrevious.contains(this)