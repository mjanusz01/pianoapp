package com.example.pianoapp.play.faketestdata

import com.example.pianoapp.notation.data.Key
import com.example.pianoapp.notation.data.Measure
import com.example.pianoapp.notation.data.Note
import com.example.pianoapp.notation.data.NoteDuration
import com.example.pianoapp.notation.data.NotePitch
import com.example.pianoapp.play.Beat

val exampleBeat1 = Beat(
    notes = listOf(
        Note(NotePitch.E3, 0F, NoteDuration.EIGHT_NOTE, Key.VIOLIN_KEY),
        Note(NotePitch.E3, 0.5F, NoteDuration.EIGHT_NOTE, Key.VIOLIN_KEY),
        Note(NotePitch.E3, 1F, NoteDuration.QUARTER_NOTE, Key.VIOLIN_KEY),
        Note(NotePitch.F3, 2F, NoteDuration.HALF_NOTE, Key.VIOLIN_KEY),
    ),
    measure = Measure.MEASURE_FOUR_QUARTER,
)

val exampleBeat2 = Beat(
    notes = listOf(
        Note(NotePitch.G3, 0F, NoteDuration.QUARTER_NOTE, Key.VIOLIN_KEY),
        Note(NotePitch.F3, 1F, NoteDuration.QUARTER_NOTE, Key.VIOLIN_KEY),
        Note(NotePitch.E3, 2F, NoteDuration.QUARTER_NOTE, Key.VIOLIN_KEY),
        Note(NotePitch.D3, 3F, NoteDuration.QUARTER_NOTE, Key.VIOLIN_KEY),
    ),
    measure = Measure.MEASURE_FOUR_QUARTER,
)

val exampleBeat3 = Beat(
    notes = listOf(
        Note(NotePitch.C3, 0F, NoteDuration.QUARTER_NOTE, Key.VIOLIN_KEY),
        Note(NotePitch.C3, 1F, NoteDuration.QUARTER_NOTE, Key.VIOLIN_KEY),
        Note(NotePitch.D3, 2F, NoteDuration.QUARTER_NOTE, Key.VIOLIN_KEY),
        Note(NotePitch.E3, 3F, NoteDuration.QUARTER_NOTE, Key.VIOLIN_KEY),
    ),
    measure = Measure.MEASURE_FOUR_QUARTER,
)

val exampleBeat4 = Beat(
    notes = listOf(
        Note(NotePitch.E3, 0F, NoteDuration.QUARTER_NOTE, Key.VIOLIN_KEY),
        Note(NotePitch.D3, 1F, NoteDuration.QUARTER_NOTE, Key.VIOLIN_KEY),
        Note(NotePitch.D3, 2F, NoteDuration.HALF_NOTE, Key.VIOLIN_KEY),
    ),
    measure = Measure.MEASURE_FOUR_QUARTER,
)

val exampleBeat5 = Beat(
    notes = listOf(
        Note(NotePitch.G1, 0F, NoteDuration.WHOLE_NOTE, Key.BASS_KEY),
        Note(NotePitch.C2, 0F, NoteDuration.WHOLE_NOTE, Key.BASS_KEY),
        Note(NotePitch.E2, 0F, NoteDuration.WHOLE_NOTE, Key.BASS_KEY),
        Note(NotePitch.C3, 2F, NoteDuration.HALF_NOTE, Key.BASS_KEY)
    ),
    measure = Measure.MEASURE_FOUR_QUARTER,
)

val exampleBeat6 = Beat(
    notes = listOf(
        Note(NotePitch.G1, 0F, NoteDuration.WHOLE_NOTE, Key.BASS_KEY),
        Note(NotePitch.H1, 0F, NoteDuration.WHOLE_NOTE, Key.BASS_KEY),
        Note(NotePitch.D2, 0F, NoteDuration.WHOLE_NOTE, Key.BASS_KEY),
    ),
    measure = Measure.MEASURE_FOUR_QUARTER,
)

val exampleBeat7 = Beat(
    notes = listOf(
        Note(NotePitch.G1, 0F, NoteDuration.WHOLE_NOTE, Key.BASS_KEY),
        Note(NotePitch.C2, 0F, NoteDuration.WHOLE_NOTE, Key.BASS_KEY),
        Note(NotePitch.E2, 0F, NoteDuration.WHOLE_NOTE, Key.BASS_KEY),
    ),
    measure = Measure.MEASURE_FOUR_QUARTER,
)

val exampleBeat8 = Beat(
    notes = listOf(
        Note(NotePitch.G1, 0F, NoteDuration.WHOLE_NOTE, Key.BASS_KEY),
        Note(NotePitch.H1, 0F, NoteDuration.WHOLE_NOTE, Key.BASS_KEY),
        Note(NotePitch.D2, 0F, NoteDuration.WHOLE_NOTE, Key.BASS_KEY),
    ),
    measure = Measure.MEASURE_FOUR_QUARTER,

)

val odeToJoyViolinKey = listOf(exampleBeat1, exampleBeat2, exampleBeat3, exampleBeat4)
val odeToJoyBassKey = listOf(exampleBeat5, exampleBeat6, exampleBeat7, exampleBeat8)