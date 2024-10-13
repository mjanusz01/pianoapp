package com.example.pianoapp.play

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.pianoapp.keyboard.KeyboardPartType
import com.example.pianoapp.notation.data.NotePitch
import com.example.pianoapp.ui.theme.PianoAppTheme

@Composable
@Preview(widthDp = 800, heightDp = 400)
fun PlayScreenContentPreview() {
    PianoAppTheme {
        PlayScreenContent(
            listOf(
                KeyboardPartType.Key(NotePitch.C1, 1F),
                KeyboardPartType.Key(NotePitch.D1, 1F),
                KeyboardPartType.Key(NotePitch.E1, 1F),
                KeyboardPartType.Key(NotePitch.F1, 1F),
                KeyboardPartType.Key(NotePitch.G1, 1F),
                KeyboardPartType.Key(NotePitch.A1, 1F),
                KeyboardPartType.Key(NotePitch.H1, 1F),
                KeyboardPartType.Key(NotePitch.C2, 1F),
                KeyboardPartType.Key(NotePitch.D2, 1F),
                KeyboardPartType.Key(NotePitch.E2, 1F),
                KeyboardPartType.Key(NotePitch.F2, 1F),
                KeyboardPartType.Key(NotePitch.G2, 1F),
                KeyboardPartType.Key(NotePitch.A2, 1F),
                KeyboardPartType.Key(NotePitch.H2, 1F),
                KeyboardPartType.Key(NotePitch.C3, 1F),
                KeyboardPartType.Key(NotePitch.D3, 1F),
                KeyboardPartType.Key(NotePitch.E3, 1F),
                KeyboardPartType.Key(NotePitch.F3, 1F),
                KeyboardPartType.Key(NotePitch.G3, 1F),
                KeyboardPartType.Key(NotePitch.A3, 1F),
                KeyboardPartType.Key(NotePitch.H3, 1F),
                KeyboardPartType.Key(NotePitch.C4, 1F),
            ),
            listOf(
                KeyboardPartType.EmptySpace(0.5F),
                KeyboardPartType.EmptySpace(0.15F),
                KeyboardPartType.Key(NotePitch.CIS1, 0.7F),
                KeyboardPartType.EmptySpace(0.15F),
                KeyboardPartType.EmptySpace(0.15F),
                KeyboardPartType.Key(NotePitch.DIS1, 0.7F),
                KeyboardPartType.EmptySpace(1.15F),
                KeyboardPartType.EmptySpace(0.15F),
                KeyboardPartType.Key(NotePitch.FIS1, 0.7F),
                KeyboardPartType.EmptySpace(0.15F),
                KeyboardPartType.EmptySpace(0.15F),
                KeyboardPartType.Key(NotePitch.GIS1, 0.7F),
                KeyboardPartType.EmptySpace(0.15F),
                KeyboardPartType.EmptySpace(0.15F),
                KeyboardPartType.Key(NotePitch.B1, 0.7F),
                KeyboardPartType.EmptySpace(1.15F),
                KeyboardPartType.EmptySpace(0.15F),
                KeyboardPartType.Key(NotePitch.CIS1, 0.7F),
                KeyboardPartType.EmptySpace(0.15F),
                KeyboardPartType.EmptySpace(0.15F),
                KeyboardPartType.Key(NotePitch.DIS1, 0.7F),
                KeyboardPartType.EmptySpace(1.15F),
                KeyboardPartType.EmptySpace(0.15F),
                KeyboardPartType.Key(NotePitch.FIS1, 0.7F),
                KeyboardPartType.EmptySpace(0.15F),
                KeyboardPartType.EmptySpace(0.15F),
                KeyboardPartType.Key(NotePitch.GIS1, 0.7F),
                KeyboardPartType.EmptySpace(0.15F),
                KeyboardPartType.EmptySpace(0.15F),
                KeyboardPartType.Key(NotePitch.B1, 0.7F),
                KeyboardPartType.EmptySpace(1.15F),
                KeyboardPartType.EmptySpace(0.15F),
                KeyboardPartType.Key(NotePitch.CIS1, 0.7F),
                KeyboardPartType.EmptySpace(0.15F),
                KeyboardPartType.EmptySpace(0.15F),
                KeyboardPartType.Key(NotePitch.DIS1, 0.7F),
                KeyboardPartType.EmptySpace(1.15F),
                KeyboardPartType.EmptySpace(0.15F),
                KeyboardPartType.Key(NotePitch.FIS1, 0.7F),
                KeyboardPartType.EmptySpace(0.15F),
                KeyboardPartType.EmptySpace(0.15F),
                KeyboardPartType.Key(NotePitch.GIS1, 0.7F),
                KeyboardPartType.EmptySpace(0.15F),
                KeyboardPartType.EmptySpace(0.15F),
                KeyboardPartType.Key(NotePitch.B1, 0.7F),
                KeyboardPartType.EmptySpace(1.15F),
                KeyboardPartType.EmptySpace(0.5F),

                ),
//            listOf(
//                listOf(
//                    Note(
//                        noteDuration = NoteDuration.WHOLE_NOTE,
//                        heightTopOffset = 8.12F,
//                        heightBottomOffset = 15.15F,
//                        tailType = TailType.NO_TAIL
//                    ),
//                    NotationObject.Note(
//                        noteDuration = NoteDuration.QUARTER_NOTE,
//                        heightTopOffset = 7.09F,
//                        heightBottomOffset = 16.18F,
//                        tailType = TailType.STRAIGHT_TAIL_AFTER
//
//                    ),
//                    NotationObject.Note(
//
//                        noteDuration = NoteDuration.HALF_NOTE,
//                        heightTopOffset = 7.62F,
//                        heightBottomOffset = 15.65F,
//                        tailType = TailType.STRAIGHT_TAIL_AFTER
//
//                    ),
//                    NotationObject.Note(
//
//                        noteDuration = NoteDuration.HALF_NOTE,
//                        heightTopOffset = 6.59F,
//                        heightBottomOffset = 16.68F,
//                        tailType = TailType.NO_TAIL
//
//                    ),
//                    NotationObject.Note(
//                        noteDuration = NoteDuration.HALF_NOTE,
//                        heightTopOffset = 5.03F,
//                        heightBottomOffset = 18.24F,
//                        tailType = TailType.STRAIGHT_TAIL_BEFORE
//
//                    ),
//                ),
//                listOf(
//                    NotationObject.Note(
//                        noteDuration = NoteDuration.HALF_NOTE,
//                        heightTopOffset = 8.12F,
//                        heightBottomOffset = 15.15F,
//                        tailType = TailType.NO_TAIL
//
//                    ),
//                    NotationObject.Note(
//                        noteDuration = NoteDuration.QUARTER_NOTE,
//                        heightTopOffset = 7.09F,
//                        heightBottomOffset = 16.18F,
//                        tailType = TailType.NO_TAIL
//
//                    ),
//                    NotationObject.Note(
//                        noteDuration = NoteDuration.WHOLE_NOTE,
//                        heightTopOffset = 7.62F,
//                        heightBottomOffset = 15.65F,
//                        tailType = TailType.NO_TAIL
//
//                    ),
//                    NotationObject.Note(
//                        noteDuration = NoteDuration.HALF_NOTE,
//                        heightTopOffset = 5.03F,
//                        heightBottomOffset = 18.24F,
//                        tailType = TailType.NO_TAIL
//
//                    ),
//                ),
//                listOf(
//                    NotationObject.Note(
//                        noteDuration = NoteDuration.HALF_NOTE,
//                        heightTopOffset = 8.12F,
//                        heightBottomOffset = 15.15F,
//                        tailType = TailType.NO_TAIL
//
//                    ),
//                    NotationObject.Note(
//                        noteDuration = NoteDuration.QUARTER_NOTE,
//                        heightTopOffset = 7.09F,
//                        heightBottomOffset = 16.18F,
//                        tailType = TailType.NO_TAIL
//
//                    ),
//                    NotationObject.Note(
//                        noteDuration = NoteDuration.WHOLE_NOTE,
//                        heightTopOffset = 7.62F,
//                        heightBottomOffset = 15.65F,
//                        tailType = TailType.NO_TAIL
//
//                    ),
//                    NotationObject.Note(
//                        noteDuration = NoteDuration.HALF_NOTE,
//                        heightTopOffset = 5.03F,
//                        heightBottomOffset = 18.24F,
//                        tailType = TailType.NO_TAIL
//
//                    ),
//                ),
//                listOf(
//                    NotationObject.Note(
//                        noteDuration = NoteDuration.HALF_NOTE,
//                        heightTopOffset = 8.12F,
//                        heightBottomOffset = 15.15F,
//                        tailType = TailType.NO_TAIL
//
//                    ),
//                    NotationObject.Note(
//                        noteDuration = NoteDuration.QUARTER_NOTE,
//                        heightTopOffset = 7.09F,
//                        heightBottomOffset = 16.18F,
//                        tailType = TailType.NO_TAIL
//
//                    ),
//                    NotationObject.Note(
//                        noteDuration = NoteDuration.WHOLE_NOTE,
//                        heightTopOffset = 7.62F,
//                        heightBottomOffset = 15.65F,
//                        tailType = TailType.NO_TAIL
//
//                    ),
//                    NotationObject.Note(
//                        noteDuration = NoteDuration.HALF_NOTE,
//                        heightTopOffset = 5.03F,
//                        heightBottomOffset = 18.24F,
//                        tailType = TailType.NO_TAIL
//                    ),
//                ),
//            )
        )
    }
}