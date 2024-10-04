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
import androidx.compose.ui.unit.dp
import com.example.pianoapp.keyboard.KeyboardPartType
import com.example.pianoapp.keyboard.KeyboardViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.compose.viewModel

@Composable
fun KeyboardComponent() {

    val keyboardViewModel: KeyboardViewModel = koinViewModel()
    val keyboardState by keyboardViewModel.uiState.collectAsState()

    Row(
        Modifier.fillMaxSize()
    ) {
        keyboardState.whiteKeysRenderData.forEach {
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
        keyboardState.blackKeysRenderData.forEach {
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

//@Composable
//@Preview(widthDp = 800, heightDp = 300)
//fun KeyboardComponentPreview() {
//    PianoAppTheme {
//        KeyboardComponent(
//            listOf(
//                KeyboardPartType.Key(Note.C1, 1F, true),
//                KeyboardPartType.Key(Note.D1, 1F),
//                KeyboardPartType.Key(Note.E1, 1F),
//                KeyboardPartType.Key(Note.F1, 1F),
//                KeyboardPartType.Key(Note.G1, 1F, true),
//                KeyboardPartType.Key(Note.A1, 1F),
//                KeyboardPartType.Key(Note.H1, 1F),
//                KeyboardPartType.Key(Note.C2, 1F, true),
//            ),
//            listOf(
//                KeyboardPartType.EmptySpace(0.5F),
//                KeyboardPartType.EmptySpace(0.15F),
//                KeyboardPartType.Key(Note.CIS1, 0.7F),
//                KeyboardPartType.EmptySpace(0.15F),
//                KeyboardPartType.EmptySpace(0.15F),
//                KeyboardPartType.Key(Note.DIS1, 0.7F, true),
//                KeyboardPartType.EmptySpace(1.15F),
//                KeyboardPartType.EmptySpace(0.15F),
//                KeyboardPartType.Key(Note.FIS1, 0.7F),
//                KeyboardPartType.EmptySpace(0.15F),
//                KeyboardPartType.EmptySpace(0.15F),
//                KeyboardPartType.Key(Note.GIS1, 0.7F),
//                KeyboardPartType.EmptySpace(0.15F),
//                KeyboardPartType.EmptySpace(0.15F),
//                KeyboardPartType.Key(Note.B1, 0.7F),
//                KeyboardPartType.EmptySpace(1.15F),
//                KeyboardPartType.EmptySpace(0.5F),
//            )
//        )
//    }
//}

//@Composable
//@Preview(widthDp = 600, heightDp = 300)
//fun KeyboardComponentPreview2() {
//    PianoAppTheme {
//        KeyboardComponent(
//            listOf(
//                KeyboardPartType.Key(Note.C1, 1F),
//                KeyboardPartType.Key(Note.D1, 1F),
//                KeyboardPartType.Key(Note.E1, 1F, true),
//                KeyboardPartType.Key(Note.F1, 1F),
//                KeyboardPartType.Key(Note.G1, 1F),
//                KeyboardPartType.Key(Note.A1, 1F),
//            ),
//            listOf(
//                KeyboardPartType.EmptySpace(0.5F),
//                KeyboardPartType.EmptySpace(0.15F),
//                KeyboardPartType.Key(Note.CIS1, 0.7F),
//                KeyboardPartType.EmptySpace(0.15F),
//                KeyboardPartType.EmptySpace(0.15F),
//                KeyboardPartType.Key(Note.DIS1, 0.7F),
//                KeyboardPartType.EmptySpace(1.15F),
//                KeyboardPartType.EmptySpace(0.15F),
//                KeyboardPartType.Key(Note.FIS1, 0.7F),
//                KeyboardPartType.EmptySpace(0.15F),
//                KeyboardPartType.EmptySpace(0.15F),
//                KeyboardPartType.Key(Note.GIS1, 0.7F, true),
//                KeyboardPartType.EmptySpace(0.15F),
//                KeyboardPartType.EmptySpace(0.5F),
//            )
//        )
//    }
//}

val KEYBOARD_COMPONENT = "keyboard"


