package com.example.pianoapp.keyboard

import com.example.pianoapp.connection.usecase.parser.NotePitch
import junit.framework.TestCase.assertEquals
import org.junit.Test

class KeyboardViewModelTest {


    @Test
    fun keyboardWithWhiteBoundsIsRenderedCorrectly() {

        val expectedWhiteKeys = listOf(
            KeyboardPartType.Key(NotePitch.C1, 1F),
            KeyboardPartType.Key(NotePitch.D1, 1F),
            KeyboardPartType.Key(NotePitch.E1, 1F),
            KeyboardPartType.Key(NotePitch.F1, 1F),
            KeyboardPartType.Key(NotePitch.G1, 1F),
            KeyboardPartType.Key(NotePitch.A1, 1F),
            KeyboardPartType.Key(NotePitch.H1, 1F),
            KeyboardPartType.Key(NotePitch.C2, 1F),
        )

        val expectedBlackKeys = listOf(
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
            KeyboardPartType.EmptySpace(0.5F),
        )


        val viewModel = KeyboardViewModel()
        viewModel.renderKeyboardComponent(NotePitch.C1, NotePitch.C2)

        assertEquals(expectedWhiteKeys, viewModel.uiState.value.whiteKeysRenderData)
        assertEquals(expectedBlackKeys, viewModel.uiState.value.blackKeysRenderData)
    }

    @Test
    fun keyboardWithBlackBoundsIsRenderedCorrectly(){
        val expectedWhiteKeys = listOf(
            KeyboardPartType.Key(NotePitch.C1, 1F),
            KeyboardPartType.Key(NotePitch.D1, 1F),
            KeyboardPartType.Key(NotePitch.E1, 1F),
            KeyboardPartType.Key(NotePitch.F1, 1F),
            KeyboardPartType.Key(NotePitch.G1, 1F),
            KeyboardPartType.Key(NotePitch.A1, 1F),
        )

        val expectedBlackKeys = listOf(
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
            KeyboardPartType.EmptySpace(0.5F),
        )

        val viewModel = KeyboardViewModel()
        viewModel.renderKeyboardComponent(NotePitch.CIS1, NotePitch.GIS1)

        assertEquals(expectedWhiteKeys, viewModel.uiState.value.whiteKeysRenderData)
        assertEquals(expectedBlackKeys, viewModel.uiState.value.blackKeysRenderData)
    }
}