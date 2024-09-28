package com.example.pianoapp.keyboard

import com.example.pianoapp.connection.usecase.parser.Note
import junit.framework.TestCase.assertEquals
import org.junit.Test

class KeyboardViewModelTest {


    @Test
    fun keyboardWithWhiteBoundsIsRenderedCorrectly() {

        val expectedWhiteKeys = listOf(
            KeyboardPartType.Key(Note.C1, 1F),
            KeyboardPartType.Key(Note.D1, 1F),
            KeyboardPartType.Key(Note.E1, 1F),
            KeyboardPartType.Key(Note.F1, 1F),
            KeyboardPartType.Key(Note.G1, 1F),
            KeyboardPartType.Key(Note.A1, 1F),
            KeyboardPartType.Key(Note.H1, 1F),
            KeyboardPartType.Key(Note.C2, 1F),
        )

        val expectedBlackKeys = listOf(
            KeyboardPartType.EmptySpace(0.5F),
            KeyboardPartType.EmptySpace(0.15F),
            KeyboardPartType.Key(Note.CIS1, 0.7F),
            KeyboardPartType.EmptySpace(0.15F),
            KeyboardPartType.EmptySpace(0.15F),
            KeyboardPartType.Key(Note.DIS1, 0.7F),
            KeyboardPartType.EmptySpace(1.15F),
            KeyboardPartType.EmptySpace(0.15F),
            KeyboardPartType.Key(Note.FIS1, 0.7F),
            KeyboardPartType.EmptySpace(0.15F),
            KeyboardPartType.EmptySpace(0.15F),
            KeyboardPartType.Key(Note.GIS1, 0.7F),
            KeyboardPartType.EmptySpace(0.15F),
            KeyboardPartType.EmptySpace(0.15F),
            KeyboardPartType.Key(Note.B1, 0.7F),
            KeyboardPartType.EmptySpace(1.15F),
            KeyboardPartType.EmptySpace(0.5F),
        )


        val viewModel = KeyboardViewModel()
        viewModel.renderKeyboardComponent(Note.C1, Note.C2)

        assertEquals(expectedWhiteKeys, viewModel.uiState.value.whiteKeysRenderData)
        assertEquals(expectedBlackKeys, viewModel.uiState.value.blackKeysRenderData)
    }

    @Test
    fun keyboardWithBlackBoundsIsRenderedCorrectly(){
        val expectedWhiteKeys = listOf(
            KeyboardPartType.Key(Note.C1, 1F),
            KeyboardPartType.Key(Note.D1, 1F),
            KeyboardPartType.Key(Note.E1, 1F),
            KeyboardPartType.Key(Note.F1, 1F),
            KeyboardPartType.Key(Note.G1, 1F),
            KeyboardPartType.Key(Note.A1, 1F),
        )

        val expectedBlackKeys = listOf(
            KeyboardPartType.EmptySpace(0.5F),
            KeyboardPartType.EmptySpace(0.15F),
            KeyboardPartType.Key(Note.CIS1, 0.7F),
            KeyboardPartType.EmptySpace(0.15F),
            KeyboardPartType.EmptySpace(0.15F),
            KeyboardPartType.Key(Note.DIS1, 0.7F),
            KeyboardPartType.EmptySpace(1.15F),
            KeyboardPartType.EmptySpace(0.15F),
            KeyboardPartType.Key(Note.FIS1, 0.7F),
            KeyboardPartType.EmptySpace(0.15F),
            KeyboardPartType.EmptySpace(0.15F),
            KeyboardPartType.Key(Note.GIS1, 0.7F),
            KeyboardPartType.EmptySpace(0.15F),
            KeyboardPartType.EmptySpace(0.5F),
        )

        val viewModel = KeyboardViewModel()
        viewModel.renderKeyboardComponent(Note.CIS1, Note.GIS1)

        assertEquals(expectedWhiteKeys, viewModel.uiState.value.whiteKeysRenderData)
        assertEquals(expectedBlackKeys, viewModel.uiState.value.blackKeysRenderData)
    }
}