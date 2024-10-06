package com.example.pianoapp.connection.usecase.parser

val blackKeys = listOf(
    NotePitch.CIS0, NotePitch.DIS0, NotePitch.FIS0, NotePitch.GIS0, NotePitch.B0,
    NotePitch.CIS1, NotePitch.DIS1, NotePitch.FIS1, NotePitch.GIS1, NotePitch.B1,
    NotePitch.CIS2, NotePitch.DIS2, NotePitch.FIS2, NotePitch.GIS2, NotePitch.B2,
    NotePitch.CIS3, NotePitch.DIS3, NotePitch.FIS3, NotePitch.GIS3, NotePitch.B3,
    NotePitch.CIS4, NotePitch.DIS4, NotePitch.FIS4, NotePitch.GIS4, NotePitch.B4,
)

val blackKeysWithMoreSpace = listOf(NotePitch.DIS0, NotePitch.B0, NotePitch.DIS1, NotePitch.B1, NotePitch.DIS2, NotePitch.B2, NotePitch.DIS3, NotePitch.B3, NotePitch.DIS4, NotePitch.B4, NotePitch.DIS5, NotePitch.B5)

val whiteKeysWithNoBlackKeyPrevious = listOf(NotePitch.C0, NotePitch.F0, NotePitch.C1, NotePitch.F1, NotePitch.C2, NotePitch.F2, NotePitch.C3, NotePitch.F3, NotePitch.C4, NotePitch.F4, NotePitch.C5, NotePitch.F5)

fun NotePitch.isBlack() = blackKeys.contains(this)

fun NotePitch.isWhite() = !this.isBlack()

fun NotePitch.getPreviousOrFirstPossibleNote(): NotePitch {
    val index = NotePitch.entries.indexOf(this) - 1
    if(index < 0){
        return NotePitch.C0
    } else{
        return NotePitch.entries[index]
    }
}

fun NotePitch.getNextOrLastPossibleNote() : NotePitch {
    val index = NotePitch.entries.indexOf(this) + 1
    if(index > NotePitch.entries.size){
        return NotePitch.C6
    } else{
        return NotePitch.entries[index]
    }
}

fun NotePitch.hasNextKeyMoreSpace(): Boolean = blackKeysWithMoreSpace.contains(this)

fun NotePitch.hasNoPreviousBlackKey(): Boolean = whiteKeysWithNoBlackKeyPrevious.contains(this)