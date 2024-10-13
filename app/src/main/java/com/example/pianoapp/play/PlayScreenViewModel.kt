package com.example.pianoapp.play

import androidx.lifecycle.ViewModel
import com.example.pianoapp.notation.data.Key
import com.example.pianoapp.notation.data.Measure
import com.example.pianoapp.notation.data.Note
import com.example.pianoapp.notation.data.NoteDuration
import com.example.pianoapp.notation.data.NotePitch
import com.example.pianoapp.notation.data.TailType
import com.example.pianoapp.play.faketestdata.odeToJoyBassKey
import com.example.pianoapp.play.faketestdata.odeToJoyViolinKey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class PlayScreenViewModel : ViewModel() {

   private val _uiState = MutableStateFlow(PlayScreenUiState())
   val uiState: StateFlow<PlayScreenUiState> = _uiState

   init {
      createNotesToGenerate(
         odeToJoyViolinKey,
         odeToJoyBassKey
      )
   }

   fun createNotesToGenerate(
      violinKeyBeats: List<Beat>,
      bassKeyBeats: List<Beat>
   ) {
      _uiState.update {
         it.copy(
            beatsToGenerate = violinKeyBeats
               .zip(bassKeyBeats) { a, b -> Beat(notes = a.notes + b.notes, measure = a.measure) }
               .map { beat ->
                  BeatToGenerate(
                     groupsToGenerate = beat.notes
                        .asSequence()
                        .map { it.addNoteSpecificInformation() }
                        .groupBy { it.timeOffset }
                        .toList()
                        .sortedBy { it.first }
                        .map { it.second }
                        .toList()
                        .addRightOffsets(beat.measure),
                     measure = beat.measure
                  )
               },
         )
      }
   }

   private fun List<List<NoteToGenerate>>.addRightOffsets(measure: Measure): List<NoteGroup> {
      return this.zipWithNext { current, next ->
         NoteGroup(current, current[0].timeOffset, (60*(next[0].timeOffset - current[0].timeOffset)).toInt())
      }.plus(
         NoteGroup(this.last(), this.last()[0].timeOffset, (60*(measure.beats.toFloat() - this.last()[0].timeOffset)).toInt())
      )
   }

   private fun Note.addNoteSpecificInformation(): NoteToGenerate {

      val heightTopOffset =
         if (this.key == Key.VIOLIN_KEY) this.notePitch.topOffsetForViolinKey() else this.notePitch.topOffsetForBassKey()

      val isTail = this.duration != NoteDuration.WHOLE_NOTE
      val isTailBefore = isTail && if (this.key == Key.VIOLIN_KEY) this.notePitch > NotePitch.A3 else this.notePitch > NotePitch.H1

      val tailType = when {
         (this.duration == NoteDuration.HALF_NOTE || this.duration == NoteDuration.QUARTER_NOTE) && isTailBefore -> TailType.STRAIGHT_TAIL_BEFORE
         (this.duration == NoteDuration.HALF_NOTE || this.duration == NoteDuration.QUARTER_NOTE) -> TailType.STRAIGHT_TAIL_AFTER
         (this.duration == NoteDuration.EIGHT_NOTE) && isTailBefore -> TailType.EIGHT_TAIL_BEFORE
         (this.duration == NoteDuration.EIGHT_NOTE) -> TailType.EIGHT_TAIL_AFTER
         else -> TailType.NO_TAIL
      }

      return NoteToGenerate(
         this.noteOffset,
         this.duration,
         heightTopOffset,
         23F - heightTopOffset,
         tailType
      )
   }
}

data class PlayScreenUiState(
   val beatsToGenerate: List<BeatToGenerate> = emptyList(),
)

data class NoteGroup(
   val notes: List<NoteToGenerate>,
   val offset: Float,
   val rightOffset: Int
)

data class Beat(
   val notes: List<Note>,
   val measure: Measure,
)

data class BeatToGenerate(
   val groupsToGenerate: List<NoteGroup>,
   val measure: Measure
)

data class NoteToGenerate(
   val timeOffset: Float,
   val noteDuration: NoteDuration,
   val heightTopOffset: Float,
   val heightBottomOffset: Float,
   val tailType: TailType,
)

