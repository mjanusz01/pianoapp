package com.example.pianoapp.play

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
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
    noteDuration: NoteDuration,
    tailType: TailType
) {
    Row(Modifier.fillMaxSize()) {
        if (leftOffset > 0) {
            Spacer(Modifier.weight(leftOffset))
        }
        Box{
            Column(
            ){
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
                    tint = Color.White
                )
                if (bottomOffset > 0) {
                    Spacer(Modifier.weight(bottomOffset))
                }
            }
            if (tailType == TailType.BEFORE){
                Column(
                    Modifier.width(1.dp).fillMaxHeight().align(Alignment.CenterStart).zIndex(1f)
                ) {
                    if (topOffset > 0) {
                        Spacer(Modifier.weight(topOffset + 0.2F))
                    }
                    Column(
                        Modifier.weight(3F).width(1.dp).background(Color.White)
                    ){
                    }
                    if (bottomOffset > 0) {
                        Spacer(Modifier.weight(bottomOffset - 3.0F))
                    }
                }
            }
            if (tailType == TailType.AFTER){
                Column (
                    Modifier.width(1.dp).fillMaxHeight().align(Alignment.CenterEnd).zIndex(1f)
                ){
                    if (topOffset > 0) {
                        Spacer(Modifier.weight(topOffset - 3.0F))
                    }
                    Column(
                        Modifier.weight(3F).width(1.dp).background(Color.White)
                    ){
                    }
                    if (bottomOffset > 0) {
                        Spacer(Modifier.weight(bottomOffset + 0.2F))
                    }
                }
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
                    it.noteDuration,
                    it.tailType
                )
            }
        }
    }
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

val PLAY_SCREEN_ROUTE = "play"