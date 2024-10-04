package com.example.pianoapp.dashboard.ui.data

import android.view.Menu
import com.example.pianoapp.R
import com.example.pianoapp.connection.ui.CONNECT_SCREEN_ROUTE
import com.example.pianoapp.dashboard.ui.DASHBOARD_SCREEN_ROUTE
import com.example.pianoapp.keyboard.ui.KEYBOARD_COMPONENT

data class MenuItemData(
    val text: String,
    val iconResource: Int,
    val type: MenuItemDataType
)

val itemsData = listOf(
    MenuItemData(
        text = "Connect",
        iconResource = R.drawable.usb_svgrepo_com_2,
        type = MenuItemDataType.CONNECT_ITEM
    ),
    MenuItemData(
        text = "Learn",
        iconResource = R.drawable.music_note_svgrepo_com,
        type = MenuItemDataType.LEARN_ITEM
    ),
    MenuItemData(
        text = "Practice",
        iconResource = R.drawable.piano_svgrepo_com_3,
        type = MenuItemDataType.PRACTICE_ITEM
    ),
    MenuItemData(
        text = "Settings",
        iconResource = R.drawable.settings_svgrepo_com,
        type = MenuItemDataType.SETTINGS
    ),
)

fun MenuItemDataType.toNavRoute() : String = when(this){
    MenuItemDataType.LEARN_ITEM -> DASHBOARD_SCREEN_ROUTE
    MenuItemDataType.CONNECT_ITEM -> CONNECT_SCREEN_ROUTE
    MenuItemDataType.PRACTICE_ITEM -> KEYBOARD_COMPONENT
    MenuItemDataType.SETTINGS -> DASHBOARD_SCREEN_ROUTE
}

enum class MenuItemDataType{
    CONNECT_ITEM,
    LEARN_ITEM,
    PRACTICE_ITEM,
    SETTINGS,
}