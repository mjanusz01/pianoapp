package com.example.pianoapp.play.faketestdata

import com.example.pianoapp.connection.usecase.parser.NotePitch
import com.example.pianoapp.play.Beat
import com.example.pianoapp.play.Key
import com.example.pianoapp.play.Measure
import com.example.pianoapp.play.NotationObject
import com.example.pianoapp.play.Note
import com.example.pianoapp.play.NoteDuration

val testPieceB21 = Beat(
    notes = listOf(
        Note(NotePitch.C2, 0F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.C3, 0F, NoteDuration.QUARTER_NOTE),

    ),
    measure = Measure.MEASURE_FOUR_QUARTER,
    key = Key.VIOLIN_KEY
)