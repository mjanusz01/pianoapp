package com.example.pianoapp.keyboard

import androidx.lifecycle.ViewModel
import com.example.pianoapp.connection.usecase.parser.KeyPressData
import com.example.pianoapp.connection.usecase.parser.Note
import com.example.pianoapp.connection.usecase.parser.getNextOrLastPossibleNote
import com.example.pianoapp.connection.usecase.parser.getPreviousOrFirstPossibleNote
import com.example.pianoapp.connection.usecase.parser.hasNextKeyMoreSpace
import com.example.pianoapp.connection.usecase.parser.isBlack
import com.example.pianoapp.connection.usecase.parser.isWhite
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class KeyboardViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(KeyboardUiState())
    val uiState: StateFlow<KeyboardUiState> = _uiState

    init {
        renderKeyboardComponent(Note.C1, Note.C3)
    }

    fun onKeyPressed(note: Note){
        val newWhiteKeysList = uiState.value.whiteKeysRenderData.map{
            if(it is KeyboardPartType.Key && it.key == note) it.copy(isPressed = true) else it
        }

        val newBlackKeysList = uiState.value.blackKeysRenderData.map{
            if(it is KeyboardPartType.Key && it.key == note) it.copy(isPressed = true) else it
        }

        _uiState.update {
            it.copy(
                whiteKeysRenderData = newWhiteKeysList,
                blackKeysRenderData = newBlackKeysList,
                test = it.test + 1
            )
        }
    }

    fun onKeyReleased(note: Note){
        _uiState.update { it ->
            it.copy(
                whiteKeysRenderData = uiState.value.whiteKeysRenderData.map{
                    if(it is KeyboardPartType.Key && it.key == note && it.isPressed) it.copy(isPressed = false) else it
                },
                blackKeysRenderData = uiState.value.blackKeysRenderData.map{
                    if(it is KeyboardPartType.Key && it.key == note && it.isPressed) it.copy(isPressed = false) else it
                },
            )
        }
        println(uiState.value.whiteKeysRenderData)
    }

    fun renderKeyboardComponent(
        firstNote: Note,
        lastNote: Note
    ) {

        // first and last note should be white
        val startNote =
            if (firstNote.isBlack()) firstNote.getPreviousOrFirstPossibleNote() else firstNote
        val endNote = if (firstNote.isBlack()) lastNote.getNextOrLastPossibleNote() else lastNote

        val whiteKeysToGenerate =
            Note.entries.filter { it.isWhite() && it >= startNote && it <= endNote }
        val blackKeysToGenerate =
            Note.entries.filter { it.isBlack() && it >= startNote && it <= endNote }

        val newWhiteKeysRenderData = whiteKeysToGenerate.map { KeyboardPartType.Key(it, 1F) }
        val newBlackKeysRenderData =
            listOf(KeyboardPartType.EmptySpace(0.5F)) + blackKeysToGenerate.flatMap {
                if (it.hasNextKeyMoreSpace()) {
                    listOf(
                        KeyboardPartType.EmptySpace(0.15F),
                        KeyboardPartType.Key(it, 0.7F),
                        KeyboardPartType.EmptySpace(1.15F)
                    )
                } else {
                    listOf(
                        KeyboardPartType.EmptySpace(0.15F),
                        KeyboardPartType.Key(it, 0.7F),
                        KeyboardPartType.EmptySpace(0.15F)
                    )
                }
            } + listOf(KeyboardPartType.EmptySpace(0.5F))

        _uiState.update {
            it.copy(
                whiteKeysRenderData = newWhiteKeysRenderData,
                blackKeysRenderData = newBlackKeysRenderData
            )
        }

    }
}

data class KeyboardUiState(
    val whiteKeysRenderData: List<KeyboardPartType> = emptyList(),
    val blackKeysRenderData: List<KeyboardPartType> = emptyList(),
    val test: Int = 0
)

sealed class KeyboardPartType {
    data class Key(val key: Note, val weight: Float, val isPressed: Boolean = false) : KeyboardPartType()
    data class EmptySpace(val weight: Float) : KeyboardPartType()
}

