package com.example.pianoapp.usecase.connection.data

data class KeyPressData(
    val keyPressData: KeyPressInfoState,
    val note: Note,
    val keyPressPower: Int
)

enum class KeyPressInfoState{
    KEY_PRESSED, KEY_RELEASED
}

enum class Note{
    C0, CIS0, D0, DIS0, E0, F0, FIS0, G0, GIS0, A0, B0, H0,
    C1, CIS1, D1, DIS1, E1, F1, FIS1, G1, GIS1, A1, B1, H1,
    C2, CIS2, D2, DIS2, E2, F2, FIS2, G2, GIS2, A2, B2, H2,
    C3, CIS3, D3, DIS3, E3, F3, FIS3, G3, GIS3, A3, B3, H3,
    C4, CIS4, D4, DIS4, E4, F4, FIS4, G4, GIS4, A4, B4, H4,
    C5, CIS5, D5, DIS5, E5, F5, FIS5, G5, GIS5, A5, B5, H5,
}