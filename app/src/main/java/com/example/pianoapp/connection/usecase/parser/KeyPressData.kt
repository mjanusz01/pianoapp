package com.example.pianoapp.connection.usecase.parser

import com.example.pianoapp.notation.data.NotePitch

data class KeyPressData(
    val keyPressData: KeyPressInfoState,
    val notePitch: NotePitch,
    val keyPressPower: Int
)

enum class KeyPressInfoState{
    KEY_PRESSED, KEY_RELEASED
}


