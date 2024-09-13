package com.example.pianoapp.dashboard.ui.data

import android.view.Menu
import com.example.pianoapp.R

data class MenuItemData(
    val text: String,
    val iconResource: Int,
    val type: MenuItemDataType
)

val itemsData = listOf(
    MenuItemData(
        text = "Connect",
        iconResource = R.drawable.connect_svgrepo_com,
        type = MenuItemDataType.CONNECT_ITEM
    ),
    MenuItemData(
        text = "Play",
        iconResource = R.drawable.piano_svgrepo_com,
        type = MenuItemDataType.PLAY_ITEM
    ),
    MenuItemData(
        text = "Learn",
        iconResource = R.drawable.learn,
        type = MenuItemDataType.LEARN_ITEM
    ),
    MenuItemData(
        text = "Practice",
        iconResource = R.drawable.day_sunny_svgrepo_com,
        type = MenuItemDataType.PRACTICE_ITEM
    ),
    MenuItemData(
        text = "Ranking",
        iconResource = R.drawable.ranking_1_svgrepo_com,
        type = MenuItemDataType.RANKING_ITEM
    )
)

enum class MenuItemDataType{
    CONNECT_ITEM,
    PLAY_ITEM,
    LEARN_ITEM,
    PRACTICE_ITEM,
    RANKING_ITEM
}