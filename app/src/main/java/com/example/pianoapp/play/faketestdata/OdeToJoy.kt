package com.example.pianoapp.play.faketestdata

import com.example.pianoapp.connection.usecase.parser.NotePitch
import com.example.pianoapp.play.Beat
import com.example.pianoapp.play.Key
import com.example.pianoapp.play.Measure
import com.example.pianoapp.play.Note
import com.example.pianoapp.play.NoteDuration

val exampleBeat1 = Beat(
    notes = listOf(
        Note(NotePitch.E3, 0F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.E3, 1F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.F3, 2F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.G3, 3F, NoteDuration.QUARTER_NOTE),
    ),
    measure = Measure.MEASURE_FOUR_QUARTER,
    key = Key.VIOLIN_KEY
)

val exampleBeat2 = Beat(
    notes = listOf(
        Note(NotePitch.G3, 0F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.F3, 1F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.E3, 2F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.D3, 3F, NoteDuration.QUARTER_NOTE),
    ),
    measure = Measure.MEASURE_FOUR_QUARTER,
    key = Key.VIOLIN_KEY

)

val exampleBeat3 = Beat(
    notes = listOf(
        Note(NotePitch.C3, 0F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.C3, 1F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.D3, 2F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.E3, 3F, NoteDuration.QUARTER_NOTE),
    ),
    measure = Measure.MEASURE_FOUR_QUARTER,
    key = Key.VIOLIN_KEY

)

val exampleBeat4 = Beat(
    notes = listOf(
        Note(NotePitch.E3, 0F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.D3, 1F, NoteDuration.QUARTER_NOTE),
        Note(NotePitch.D3, 2F, NoteDuration.HALF_NOTE),
    ),
    measure = Measure.MEASURE_FOUR_QUARTER,
    key = Key.VIOLIN_KEY

)

val exampleBeat5 = Beat(
    notes = listOf(
        Note(NotePitch.G1, 0F, NoteDuration.WHOLE_NOTE),
        Note(NotePitch.C2, 0F, NoteDuration.WHOLE_NOTE),
        Note(NotePitch.E2, 0F, NoteDuration.WHOLE_NOTE),
    ),
    measure = Measure.MEASURE_FOUR_QUARTER,
    key = Key.BASS_KEY

)

val exampleBeat6 = Beat(
    notes = listOf(
        Note(NotePitch.G1, 0F, NoteDuration.WHOLE_NOTE),
        Note(NotePitch.H1, 0F, NoteDuration.WHOLE_NOTE),
        Note(NotePitch.D2, 0F, NoteDuration.WHOLE_NOTE),
    ),
    measure = Measure.MEASURE_FOUR_QUARTER,
    key = Key.BASS_KEY

)

val exampleBeat7 = Beat(
    notes = listOf(
        Note(NotePitch.G1, 0F, NoteDuration.WHOLE_NOTE),
        Note(NotePitch.C2, 0F, NoteDuration.WHOLE_NOTE),
        Note(NotePitch.E2, 0F, NoteDuration.WHOLE_NOTE),
    ),
    measure = Measure.MEASURE_FOUR_QUARTER,
    key = Key.BASS_KEY

)

val exampleBeat8 = Beat(
    notes = listOf(
        Note(NotePitch.G1, 0F, NoteDuration.WHOLE_NOTE),
        Note(NotePitch.H1, 0F, NoteDuration.WHOLE_NOTE),
        Note(NotePitch.D2, 0F, NoteDuration.WHOLE_NOTE),
    ),
    measure = Measure.MEASURE_FOUR_QUARTER,
    key = Key.BASS_KEY

)

val odeToJoyViolinKey = listOf(exampleBeat1, exampleBeat2, exampleBeat3, exampleBeat4)
val odeToJoyBassKey = listOf(exampleBeat5, exampleBeat6, exampleBeat7, exampleBeat8)