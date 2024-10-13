package com.example.pianoapp.keyboard.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pianoapp.keyboard.KeyboardPartType
import com.example.pianoapp.keyboard.KeyboardViewModel
import com.example.pianoapp.notation.data.NotePitch
import com.example.pianoapp.ui.theme.PianoAppTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.compose.viewModel

@Composable
fun KeyboardComponent(){
    val keyboardViewModel: KeyboardViewModel = koinViewModel()
    val keyboardState by keyboardViewModel.uiState.collectAsState()

    KeyboardComponentContent(keyboardState.whiteKeysRenderData, keyboardState.blackKeysRenderData)
}

@Composable
fun KeyboardComponentContent(
    whiteKeysRenderData: List<KeyboardPartType>,
    blackKeysRenderData: List<KeyboardPartType>
) {
    Row(
        Modifier.fillMaxSize()
    ) {
        whiteKeysRenderData.forEach {
            when (it) {
                is KeyboardPartType.Key -> {
                    KeyboardWhiteButton(
                        onClick = {},
                        modifier = Modifier.weight(it.weight),
                        isPressed = it.isPressed
                    )
                }

                is KeyboardPartType.EmptySpace -> {
                    Spacer(Modifier.weight(it.weight))
                }
            }
        }
    }
    Row(
        Modifier.fillMaxSize()
    ) {
        blackKeysRenderData.forEach {
            when (it) {
                is KeyboardPartType.Key -> {
                    KeyboardBlackButton(
                        onClick = {},
                        modifier = Modifier.weight(it.weight),
                        isPressed = it.isPressed
                    )
                }

                is KeyboardPartType.EmptySpace -> {
                    Spacer(Modifier.weight(it.weight))
                }
            }
        }
    }
}

@Composable
fun KeyboardWhiteButton(
    onClick: () -> Unit,
    modifier: Modifier,
    isPressed: Boolean
) {
    Row(
        modifier = modifier.fillMaxHeight()
    ) {
        Spacer(Modifier.weight(1F))
        Column(
            Modifier
                .weight(if (isPressed) 8F else 6F)
                .fillMaxHeight()
                .background(
                    color = if (isPressed) Color(0xFF99292d) else Color.White,
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable {
                    onClick()
                }
        ) {

        }
        Spacer(Modifier.weight(1F))
    }
}

@Composable
fun KeyboardBlackButton(
    onClick: () -> Unit,
    modifier: Modifier,
    isPressed: Boolean
) {
    Row(
        modifier = modifier.fillMaxHeight()
    ) {
        Spacer(Modifier.weight(1F))
        Column(
            Modifier
                .weight(if (isPressed) 8F else 6F)
                .fillMaxHeight(0.58F)
                .background(
                    color = if (isPressed) Color(0xFF99292d) else Color.Black,
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable {
                    onClick()
                }
        ) {

        }
        Spacer(Modifier.weight(1F))
    }
}

@Composable
@Preview(widthDp = 800, heightDp = 300)
fun KeyboardComponentPreview() {
    PianoAppTheme {
        KeyboardComponentContent(
            listOf(
                KeyboardPartType.Key(NotePitch.C1, 1F, true),
                KeyboardPartType.Key(NotePitch.D1, 1F),
                KeyboardPartType.Key(NotePitch.E1, 1F),
                KeyboardPartType.Key(NotePitch.F1, 1F),
                KeyboardPartType.Key(NotePitch.G1, 1F, true),
                KeyboardPartType.Key(NotePitch.A1, 1F),
                KeyboardPartType.Key(NotePitch.H1, 1F),
                KeyboardPartType.Key(NotePitch.C2, 1F, true),
            ),
            listOf(
                KeyboardPartType.EmptySpace(0.5F),
                KeyboardPartType.EmptySpace(0.15F),
                KeyboardPartType.Key(NotePitch.CIS1, 0.7F),
                KeyboardPartType.EmptySpace(0.15F),
                KeyboardPartType.EmptySpace(0.15F),
                KeyboardPartType.Key(NotePitch.DIS1, 0.7F, true),
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
            )
        )
    }
}

@Composable
@Preview(widthDp = 600, heightDp = 300)
fun KeyboardComponentPreview2() {
    PianoAppTheme {
        KeyboardComponentContent(
            listOf(
                KeyboardPartType.Key(NotePitch.C1, 1F),
                KeyboardPartType.Key(NotePitch.D1, 1F),
                KeyboardPartType.Key(NotePitch.E1, 1F, true),
                KeyboardPartType.Key(NotePitch.F1, 1F),
                KeyboardPartType.Key(NotePitch.G1, 1F),
                KeyboardPartType.Key(NotePitch.A1, 1F),
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
                KeyboardPartType.Key(NotePitch.GIS1, 0.7F, true),
                KeyboardPartType.EmptySpace(0.15F),
                KeyboardPartType.EmptySpace(0.5F),
            )
        )
    }
}



