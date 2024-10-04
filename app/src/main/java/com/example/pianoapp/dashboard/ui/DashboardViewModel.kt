package com.example.pianoapp.dashboard.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pianoapp.R
import com.example.pianoapp.dashboard.ui.data.MenuItemDataType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardViewState())
    val uiState: StateFlow<DashboardViewState> = _uiState

    fun onItemSelected(dataType: MenuItemDataType) {
        when(dataType){
            MenuItemDataType.CONNECT_ITEM -> TODO()
            MenuItemDataType.LEARN_ITEM -> TODO()
            MenuItemDataType.PRACTICE_ITEM -> TODO()
            MenuItemDataType.SETTINGS -> TODO()
        }
    }

}

data class DashboardViewState(
    val currentPhotoNumber: Int = 0,
)