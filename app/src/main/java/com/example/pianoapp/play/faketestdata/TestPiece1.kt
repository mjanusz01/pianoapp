package com.example.pianoapp.play.faketestdata

import com.example.pianoapp.connection.usecase.parser.NotePitch
import com.example.pianoapp.play.Beat
import com.example.pianoapp.play.Key
import com.example.pianoapp.play.Measure
import com.example.pianoapp.play.Note
import com.example.pianoapp.play.NoteDuration

val testPieceB1 = Beat(
    notes = listOf(
        Note(NotePitch.C3, 0F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.E3, 0F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.G3, 0F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.A3, 1F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.H3, 2F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.C4, 3F, NoteDuration.QUARTER_NOTE),
        ),
    measure = Measure.MEASURE_FOUR_QUARTER,
    key = Key.VIOLIN_KEY
)

val testPieceB2 = Beat(
    notes = listOf(
        Note(NotePitch.C4, 0F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.E4, 0F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.G4, 0F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.F3, 1F, NoteDuration.HALF_NOTE),
        Note(NotePitch.A3, 1F, NoteDuration.HALF_NOTE),
        Note(NotePitch.C4, 1F, NoteDuration.HALF_NOTE),
        Note(NotePitch.G2, 3F, NoteDuration.QUARTER_NOTE),
    ),
    measure = Measure.MEASURE_FOUR_QUARTER,
    key = Key.VIOLIN_KEY
)

val testPieceB3 = Beat(
    notes =  listOf(
        Note(NotePitch.C3, 0F, NoteDuration.EIGHT_NOTE),
        Note(NotePitch.D3, 0.5F, NoteDuration.EIGHT_NOTE),
        Note(NotePitch.E3, 1F, NoteDuration.EIGHT_NOTE),
        Note(NotePitch.F3, 1.5F, NoteDuration.EIGHT_NOTE),
        Note(NotePitch.G3, 2F, NoteDuration.EIGHT_NOTE),
        Note(NotePitch.A3, 2.5F, NoteDuration.EIGHT_NOTE),
        Note(NotePitch.H3, 3F, NoteDuration.EIGHT_NOTE),
        Note(NotePitch.C4, 3.5F, NoteDuration.EIGHT_NOTE),
    ),
    measure = Measure.MEASURE_FOUR_QUARTER,
    key = Key.VIOLIN_KEY,
)

val testPieceB4 = Beat(
    notes =  listOf(
        Note(NotePitch.C1, 0F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.C2, 0F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.C3, 0F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.CIS1, 1F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.CIS2, 1F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.CIS3, 1F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.D1, 2F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.D2, 2F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.D3, 2F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.DIS1, 3F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.DIS2, 3F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.DIS3, 3F, NoteDuration.QUARTER_NOTE),
    ),
    measure = Measure.MEASURE_FOUR_QUARTER,
    key = Key.VIOLIN_KEY,
)

val testPieceB5 = Beat(
    notes = listOf(
        Note(NotePitch.E1, 0F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.E2, 0F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.E3, 0F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.F1, 1F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.F2, 1F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.F3, 1F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.FIS1, 2F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.FIS2, 2F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.FIS3, 2F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.G1, 3F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.G2, 3F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.G3, 3F, NoteDuration.QUARTER_NOTE),
    ),
    measure = Measure.MEASURE_FOUR_QUARTER,
    key = Key.VIOLIN_KEY
)

val testPieceB6 = Beat(
    notes =  listOf(
        Note(NotePitch.GIS1, 0F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.GIS2, 0F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.A1, 1F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.A2, 1F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.B1, 2F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.B2, 2F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.H1, 3F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.H2, 3F, NoteDuration.QUARTER_NOTE),
    ),
    measure = Measure.MEASURE_FOUR_QUARTER,
    key = Key.VIOLIN_KEY,
)

val testPieceViolinKey = listOf(testPieceB1, testPieceB2, testPieceB3)
val testPieceBassKey = listOf(testPieceB4, testPieceB5, testPieceB6)