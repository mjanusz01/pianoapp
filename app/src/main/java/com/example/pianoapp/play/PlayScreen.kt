package com.example.pianoapp.play

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pianoapp.R
import com.example.pianoapp.connection.usecase.parser.NotePitch
import com.example.pianoapp.keyboard.KeyboardPartType
import com.example.pianoapp.keyboard.KeyboardViewModel
import com.example.pianoapp.keyboard.ui.KeyboardComponentContent
import com.example.pianoapp.ui.theme.PianoAppTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlayScreen() {

    val keyboardViewModel: KeyboardViewModel = koinViewModel()
    val keyboardState by keyboardViewModel.uiState.collectAsState()

    val playScreenViewModel: PlayScreenViewModel = koinViewModel()
    val playScreenUiState by playScreenViewModel.uiState.collectAsState()

    PlayScreenContent(
        keyboardState.whiteKeysRenderData,
        keyboardState.blackKeysRenderData,
        playScreenUiState.beatsToGenerate
    )
}


@Composable
fun PlayScreenContent(
    whiteKeysRenderData: List<KeyboardPartType>,
    blackKeysRenderData: List<KeyboardPartType>,
    beatsToGenerate: List<List<NotationObject>> = emptyList()
) {
    Column(
        Modifier
            .background(Color(0xFF141414))
            .fillMaxSize()
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .weight(3F)
        ) {
            StaffHolder()
            Row {
                KeyAndMeterHolder()
                beatsToGenerate.forEach {
                    BeatHolder(Modifier.weight(1F), it)
                    BarLine(Modifier.weight(0.01F))
                }
            }
        }
        Box(
            Modifier
                .fillMaxWidth()
                .weight(1F)
        ) {
            KeyboardComponentContent(whiteKeysRenderData, blackKeysRenderData)
        }
    }
}

@Composable
fun NoteHolder(
    topOffset: Float,
    bottomOffset: Float,
    leftOffset: Float,
    rightOffset: Float,
    noteDuration: NoteDuration
) {
    Row(Modifier.fillMaxSize()) {
        if (leftOffset > 0) {
            Spacer(Modifier.weight(leftOffset))
        }
        Column {
            if (topOffset > 0) {
                Spacer(Modifier.weight(topOffset))
            }
            Icon(
                painter = painterResource(
                    when (noteDuration) {
                        NoteDuration.WHOLE_NOTE -> R.drawable.note44
                        NoteDuration.HALF_NOTE -> R.drawable.halfnote1
                        NoteDuration.QUARTER_NOTE -> R.drawable.quarternote1
                        NoteDuration.EIGHT_NOTE -> R.drawable.quarternote1
                        NoteDuration.SIXTEENTH_NOTE -> R.drawable.quarternote1
                    }
                ),
                contentDescription = "null",
                modifier = Modifier.weight(1F),
                tint = Color.White,
            )
            if (bottomOffset > 0) {
                Spacer(Modifier.weight(bottomOffset))
            }
        }
        if (rightOffset > 0) {
            Spacer(Modifier.weight(rightOffset))

        }
    }
}

@Composable
fun BarLine(
    modifier: Modifier
) {
    Column(modifier.fillMaxSize()) {
        Spacer(Modifier.weight(5F))
        Column(
            Modifier
                .fillMaxSize()
                .weight(14.3F)
                .background(Color.White)
        ) {
        }
        Spacer(Modifier.weight(5F))
    }
}

@Composable
fun KeyAndMeterHolder() {
    Row(Modifier.fillMaxHeight()) {
        Spacer(Modifier.width(10.dp))
        Column(Modifier.fillMaxHeight()) {
            Spacer(Modifier.weight(4F))
            Icon(
                painter = painterResource(R.drawable.violin_key),
                contentDescription = "null",
                tint = Color.White,
                modifier = Modifier.weight(6.15F),
            )
            Spacer(Modifier.weight(5F))
            Icon(
                painter = painterResource(R.drawable.bass_key),
                contentDescription = "null",
                tint = Color.White,
                modifier = Modifier.weight(3.32F),
            )
            Spacer(Modifier.weight(5.83F))
        }
        Spacer(Modifier.width(15.dp))
        Column(Modifier.fillMaxHeight()) {
            Spacer(Modifier.weight(5F))
            Icon(
                painter = painterResource(R.drawable.ts34),
                contentDescription = "null",
                tint = Color.White,
                modifier = Modifier.weight(4.15F),
            )
            Spacer(Modifier.weight(6F))
            Icon(
                painter = painterResource(R.drawable.ts34),
                contentDescription = "null",
                tint = Color.White,
                modifier = Modifier.weight(4.15F),
            )
            Spacer(Modifier.weight(5F))
        }
        Spacer(Modifier.width(15.dp))
    }
}

@Composable
fun BeatHolder(
    modifier: Modifier,
    notesToGenerate: List<NotationObject>
) {
    Box(modifier.fillMaxSize()) {
        notesToGenerate.forEach {
            when {
                it is NotationObject.Note -> NoteHolder(
                    it.heightTopOffset,
                    it.heightBottomOffset,
                    it.widthLeftOffset,
                    it.widthRightOffset,
                    it.noteDuration
                )

                it is NotationObject.Tail -> TailHolder(
                    it.heightTopOffset,
                    it.heightBottomOffset,
                    it.widthLeftOffset,
                    it.widthRightOffset,
                )
            }
        }
    }
}

@Composable
fun TailHolder(
    topOffset: Float,
    bottomOffset: Float,
    leftOffset: Float,
    rightOffset: Float,
) {

}

@Composable
fun StaffHolder() {
    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .weight(5F)
        ) {}
        repeat(4) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .weight(0.03F)
            ) {}
            Row(
                Modifier
                    .fillMaxWidth()
                    .weight(1F)
            ) {}
        }
        Row(
            Modifier
                .fillMaxWidth()
                .background(Color.White)
                .weight(0.03F)
        ) {}
        Row(
            Modifier
                .fillMaxWidth()
                .weight(6F)
        ) {}
        repeat(4) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .weight(0.03F)
            ) {}
            Row(
                Modifier
                    .fillMaxWidth()
                    .weight(1F)
            ) {}
        }
        Row(
            Modifier
                .fillMaxWidth()
                .background(Color.White)
                .weight(0.03F)
        ) {}
        Row(
            Modifier
                .fillMaxWidth()
                .weight(5F)
        ) {}
    }
}


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
            listOf(
                listOf(
                    NotationObject.Note(
                        noteDuration = NoteDuration.WHOLE_NOTE,
                        widthLeftOffset = 0.5F,
                        widthRightOffset = 4F,
                        heightTopOffset = 8.12F,
                        heightBottomOffset = 15.15F
                    ),
                    NotationObject.Note(
                        noteDuration = NoteDuration.QUARTER_NOTE,
                        widthLeftOffset = 1.5F,
                        widthRightOffset = 3F,
                        heightTopOffset = 7.09F,
                        heightBottomOffset = 16.18F
                    ),
                    NotationObject.Note(

                        noteDuration = NoteDuration.HALF_NOTE,
                        widthLeftOffset = 2.5F,
                        widthRightOffset = 2F,
                        heightTopOffset = 7.62F,
                        heightBottomOffset = 15.65F
                    ),
                    NotationObject.Note(

                        noteDuration = NoteDuration.HALF_NOTE,
                        widthLeftOffset = 2.5F,
                        widthRightOffset = 2F,
                        heightTopOffset = 6.59F,
                        heightBottomOffset = 16.68F
                    ),
                    NotationObject.Note(
                        noteDuration = NoteDuration.HALF_NOTE,
                        widthLeftOffset = 3.5F,
                        widthRightOffset = 1F,
                        heightTopOffset = 5.03F,
                        heightBottomOffset = 18.24F
                    ),
                ),
                listOf(
                    NotationObject.Note(
                        noteDuration = NoteDuration.HALF_NOTE,
                        widthLeftOffset = 0.5F,
                        widthRightOffset = 4F,
                        heightTopOffset = 8.12F,
                        heightBottomOffset = 15.15F
                    ),
                    NotationObject.Note(
                        noteDuration = NoteDuration.QUARTER_NOTE,
                        widthLeftOffset = 1.5F,
                        widthRightOffset = 3F,
                        heightTopOffset = 7.09F,
                        heightBottomOffset = 16.18F
                    ),
                    NotationObject.Note(
                        noteDuration = NoteDuration.WHOLE_NOTE,
                        widthLeftOffset = 2.5F,
                        widthRightOffset = 2F,
                        heightTopOffset = 7.62F,
                        heightBottomOffset = 15.65F
                    ),
                    NotationObject.Note(
                        noteDuration = NoteDuration.HALF_NOTE,
                        widthLeftOffset = 3.5F,
                        widthRightOffset = 1F,
                        heightTopOffset = 5.03F,
                        heightBottomOffset = 18.24F
                    ),
                ),
                listOf(
                    NotationObject.Note(
                        noteDuration = NoteDuration.HALF_NOTE,
                        widthLeftOffset = 0.5F,
                        widthRightOffset = 4F,
                        heightTopOffset = 8.12F,
                        heightBottomOffset = 15.15F
                    ),
                    NotationObject.Note(
                        noteDuration = NoteDuration.QUARTER_NOTE,
                        widthLeftOffset = 1.5F,
                        widthRightOffset = 3F,
                        heightTopOffset = 7.09F,
                        heightBottomOffset = 16.18F
                    ),
                    NotationObject.Note(
                        noteDuration = NoteDuration.WHOLE_NOTE,
                        widthLeftOffset = 2.5F,
                        widthRightOffset = 2F,
                        heightTopOffset = 7.62F,
                        heightBottomOffset = 15.65F
                    ),
                    NotationObject.Note(
                        noteDuration = NoteDuration.HALF_NOTE,
                        widthLeftOffset = 3.5F,
                        widthRightOffset = 1F,
                        heightTopOffset = 5.03F,
                        heightBottomOffset = 18.24F
                    ),
                ),
                listOf(
                    NotationObject.Note(
                        noteDuration = NoteDuration.HALF_NOTE,
                        widthLeftOffset = 0.5F,
                        widthRightOffset = 4F,
                        heightTopOffset = 8.12F,
                        heightBottomOffset = 15.15F
                    ),
                    NotationObject.Note(
                        noteDuration = NoteDuration.QUARTER_NOTE,
                        widthLeftOffset = 1.5F,
                        widthRightOffset = 3F,
                        heightTopOffset = 7.09F,
                        heightBottomOffset = 16.18F
                    ),
                    NotationObject.Note(
                        noteDuration = NoteDuration.WHOLE_NOTE,
                        widthLeftOffset = 2.5F,
                        widthRightOffset = 2F,
                        heightTopOffset = 7.62F,
                        heightBottomOffset = 15.65F
                    ),
                    NotationObject.Note(
                        noteDuration = NoteDuration.HALF_NOTE,
                        widthLeftOffset = 3.5F,
                        widthRightOffset = 1F,
                        heightTopOffset = 5.03F,
                        heightBottomOffset = 18.24F
                    ),
                ),
            )
        )
    }
}

val PLAY_SCREEN_ROUTE = "play"