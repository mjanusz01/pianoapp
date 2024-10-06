package com.example.pianoapp.play

import androidx.lifecycle.ViewModel
import com.example.pianoapp.connection.usecase.parser.NotePitch
import com.example.pianoapp.play.faketestdata.testPieceBassKey
import com.example.pianoapp.play.faketestdata.testPieceViolinKey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class PlayScreenViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PlayScreenUiState())
    val uiState: StateFlow<PlayScreenUiState> = _uiState

    init {
        generateBeatsForBothKeys(
            testPieceViolinKey,
            testPieceBassKey
        )
    }

    fun generateBeatsForBothKeys(
        violinKeyBeats: List<Beat>,
        bassKeyBeats: List<Beat>
    ) {

        val violinKeyBeatsToGenerate = violinKeyBeats.map {
            generateWeightForBeat(it, Key.VIOLIN_KEY)
        }

        val bassKeyBeatsToGenerate = bassKeyBeats.map {
            generateWeightForBeat(it, Key.BASS_KEY)
        }

        val beatsToGenerate =
            violinKeyBeatsToGenerate.zip(bassKeyBeatsToGenerate) { a, b -> a + b }

        _uiState.update {
            it.copy(
                beatsToGenerate = beatsToGenerate,
            )
        }
    }

    private fun generateWeightForBeat(beat: Beat, key: Key): List<NotationObject> = beat.notes.map {

        val heightTopOffset =
            if (key == Key.VIOLIN_KEY) it.notePitch.topOffsetForViolinKey() else it.notePitch.topOffsetForBassKey()

        NotationObject.Note(
            it.duration,
            it.noteOffset + 0.5F,
            beat.measure.beats.toFloat() - (it.noteOffset + 0.5F),
            heightTopOffset,
            23F - heightTopOffset
        )
    }

    fun NotePitch.topOffsetForViolinKey(): Float = when (this) {
        NotePitch.C2 -> 13F
        NotePitch.CIS2 -> 13F
        NotePitch.D2 -> 12.5F
        NotePitch.DIS2 -> 12.5F
        NotePitch.E2 -> 12F
        NotePitch.F2 -> 11.5F
        NotePitch.FIS2 -> 11.5F
        NotePitch.G2 -> 11F
        NotePitch.GIS2 -> 11F
        NotePitch.A2 -> 10.5F
        NotePitch.B2 -> 10.5F
        NotePitch.H2 -> 10F
        NotePitch.C3 -> 9.5F
        NotePitch.CIS3 -> 9.5F
        NotePitch.D3 -> 9F
        NotePitch.DIS3 -> 9F
        NotePitch.E3 -> 8.5F
        NotePitch.F3 -> 8F
        NotePitch.FIS3 -> 8F
        NotePitch.G3 -> 7.5F
        NotePitch.GIS3 -> 7.5F
        NotePitch.A3 -> 7F
        NotePitch.B3 -> 7F
        NotePitch.H3 -> 6.5F
        NotePitch.C4 -> 6F
        NotePitch.CIS4 -> 6F
        NotePitch.D4 -> 5.5F
        NotePitch.DIS4 -> 5.5F
        NotePitch.E4 -> 5F
        NotePitch.F4 -> 4.5F
        NotePitch.FIS4 -> 4.5F
        NotePitch.G4 -> 4F
        NotePitch.GIS4 -> 4F
        NotePitch.A4 -> 3.5F
        NotePitch.B4 -> 3.5F
        NotePitch.H4 -> 3F
        NotePitch.C5 -> 2.5F
        else -> 0F
    }

    fun NotePitch.topOffsetForBassKey(): Float = when (this) {
        NotePitch.C1 -> 20.5F
        NotePitch.CIS1 -> 20.5F
        NotePitch.D1 -> 20F
        NotePitch.DIS1 -> 20F
        NotePitch.E1 -> 19.5F
        NotePitch.F1 -> 19F
        NotePitch.FIS1 -> 19F
        NotePitch.G1 -> 18.5F
        NotePitch.GIS1 -> 18.5F
        NotePitch.A1 -> 18F
        NotePitch.B1 -> 18F
        NotePitch.H1 -> 17.5F
        NotePitch.C2 -> 17F
        NotePitch.CIS2 -> 17F
        NotePitch.D2 -> 16.5F
        NotePitch.DIS2 -> 16.5F
        NotePitch.E2 -> 16F
        NotePitch.F2 -> 15.5F
        NotePitch.FIS2 -> 15.5F
        NotePitch.G2 -> 15F
        NotePitch.GIS2 -> 15F
        NotePitch.A2 -> 14.5F
        NotePitch.B2 -> 14.5F
        NotePitch.H2 -> 14F
        NotePitch.C3 -> 13.5F
        NotePitch.CIS3 -> 13.5F
        NotePitch.D3 -> 13F
        NotePitch.DIS3 -> 13F
        NotePitch.E3 -> 12.5F
        NotePitch.F3 -> 12F
        NotePitch.FIS3 -> 12F
        NotePitch.G3 -> 11.5F
        else -> 0F
    }
}

data class PlayScreenUiState(
    val beatsToGenerate: List<List<NotationObject>> = emptyList(),
)

enum class NoteDuration(val duration: Float) {
    WHOLE_NOTE(4F),
    HALF_NOTE(2F),
    QUARTER_NOTE(1F),
    EIGHT_NOTE(0.5F),
    SIXTEENTH_NOTE(0.25F)
}

enum class Key {
    VIOLIN_KEY,
    BASS_KEY
}

enum class Measure(val beats: Int) {
    MEASURE_THREE_QUARTER(3),
    MEASURE_FOUR_QUARTER(4)
}

data class Note(
    val notePitch: NotePitch,
    val noteOffset: Float,
    val duration: NoteDuration,
    val isPause: Boolean = false
)

data class Beat(
    val notes: List<Note>,
    val measure: Measure,
    val key: Key
)

sealed class NotationObject {
    data class Note(
        val noteDuration: NoteDuration,
        val widthLeftOffset: Float,
        val widthRightOffset: Float,
        val heightTopOffset: Float,
        val heightBottomOffset: Float
    ) : NotationObject()

    data class Tail(
        val widthLeftOffset: Float,
        val widthRightOffset: Float,
        val heightTopOffset: Float,
        val heightBottomOffset: Float
    ) : NotationObject()
}