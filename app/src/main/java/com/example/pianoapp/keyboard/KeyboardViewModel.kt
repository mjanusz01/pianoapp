package com.example.pianoapp.keyboard

import androidx.lifecycle.ViewModel
import com.example.pianoapp.notation.data.NotePitch
import com.example.pianoapp.notation.data.getNextOrLastPossibleNote
import com.example.pianoapp.notation.data.getPreviousOrFirstPossibleNote
import com.example.pianoapp.notation.data.hasNextKeyMoreSpace
import com.example.pianoapp.notation.data.isBlack
import com.example.pianoapp.notation.data.isWhite
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class KeyboardViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(KeyboardUiState())
    val uiState: StateFlow<KeyboardUiState> = _uiState

    init {
        renderKeyboardComponent(NotePitch.C1, NotePitch.C4)
    }

    fun onKeyPressed(notePitch: NotePitch){
        val newWhiteKeysList = uiState.value.whiteKeysRenderData.map{
            if(it is KeyboardPartType.Key && it.key == notePitch) it.copy(isPressed = true) else it
        }

        val newBlackKeysList = uiState.value.blackKeysRenderData.map{
            if(it is KeyboardPartType.Key && it.key == notePitch) it.copy(isPressed = true) else it
        }

        _uiState.update {
            it.copy(
                whiteKeysRenderData = newWhiteKeysList,
                blackKeysRenderData = newBlackKeysList,
                test = it.test + 1
            )
        }
    }

    fun onKeyReleased(notePitch: NotePitch){
        _uiState.update { it ->
            it.copy(
                whiteKeysRenderData = uiState.value.whiteKeysRenderData.map{
                    if(it is KeyboardPartType.Key && it.key == notePitch && it.isPressed) it.copy(isPressed = false) else it
                },
                blackKeysRenderData = uiState.value.blackKeysRenderData.map{
                    if(it is KeyboardPartType.Key && it.key == notePitch && it.isPressed) it.copy(isPressed = false) else it
                },
            )
        }
        println(uiState.value.whiteKeysRenderData)
    }

    fun renderKeyboardComponent(
        firstNotePitch: NotePitch,
        lastNotePitch: NotePitch
    ) {

        // first and last note should be white
        val startNote =
            if (firstNotePitch.isBlack()) firstNotePitch.getPreviousOrFirstPossibleNote() else firstNotePitch
        val endNote = if (firstNotePitch.isBlack()) lastNotePitch.getNextOrLastPossibleNote() else lastNotePitch

        val whiteKeysToGenerate =
            NotePitch.entries.filter { it.isWhite() && it >= startNote && it <= endNote }
        val blackKeysToGenerate =
            NotePitch.entries.filter { it.isBlack() && it >= startNote && it <= endNote }

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
    data class Key(val key: NotePitch, val weight: Float, val isPressed: Boolean = false) : KeyboardPartType()
    data class EmptySpace(val weight: Float) : KeyboardPartType()
}

