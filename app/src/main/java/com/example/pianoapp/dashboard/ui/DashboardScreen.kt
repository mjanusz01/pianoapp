package com.example.pianoapp.dashboard.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pianoapp.R
import com.example.pianoapp.dashboard.ui.data.MenuItemData
import com.example.pianoapp.dashboard.ui.data.MenuItemDataType
import com.example.pianoapp.dashboard.ui.data.itemsData
import com.example.pianoapp.ui.theme.PianoAppTheme

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel
) {

    val uiState by viewModel.uiState.collectAsState()

    Column(
        Modifier
            .verticalScroll(state = ScrollState(0))
            .fillMaxSize()
            .background(Color.White)
    ) {
        DashboardMainContent()
        Spacer(Modifier.height(12.dp))
        DashboardMenu(
            onDashboardItemClick = { viewModel.onItemSelected(it) }
        )
        Spacer(Modifier.height(12.dp))
        DashboardRankingContent()
    }
}

@Composable
fun DashboardMenu(
    onDashboardItemClick: (MenuItemDataType) -> Unit
) {
    val items = itemsData
    Column() {
        LazyRow() {
            items(items) { itemData ->
                Spacer(Modifier.width(12.dp))
                DashboardMenuItem(
                    itemData = itemData,
                    onClick = onDashboardItemClick
                )
            }
        }
    }
}

@Composable
fun DashboardMainContent(
) {
    Column(
        Modifier
            .height(300.dp)
            .fillMaxWidth()
            .background(Color(0xFF572a0f))
    ) {

    }
}

@Composable
fun DashboardMenuItem(
    itemData: MenuItemData,
    onClick: (MenuItemDataType) -> Unit
) {
    Column(
        Modifier
            .width(120.dp)
            .height(120.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF572a0f))
            .clickable {
                onClick(itemData.type)
            }
    ) {
        Spacer(Modifier.weight(1F))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(itemData.iconResource),
                contentDescription = "Icon",
                modifier = Modifier
                    .width(45.dp)
                    .height(45.dp),
                tint = Color(0xFFf0d3c0)
            )
        }
        Box(
            modifier = Modifier.weight(2F),
            contentAlignment = Alignment.Center

        ) {
            Text(
                text = itemData.text,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(
                    Font(R.font.poppins_semibold)
                ),
                fontSize = 16.sp,
                color = Color(0xFFf0d3c0)
            )
        }
    }
}

@Composable
@Preview
fun DashboardMenuItemPreview() {
    PianoAppTheme {
        DashboardMenuItem(
            itemData = MenuItemData(
                text = "Piano",
                iconResource = R.drawable.piano_svgrepo_com,
                type = MenuItemDataType.PLAY_ITEM
            ),
            onClick = {}
        )
    }
}

@Composable
fun DashboardRankingContent() {
    Column(
        Modifier
            .height(500.dp)
            .fillMaxWidth()
            .background(Color.Gray)
    ) {}
}

@Composable
@Preview
fun DashboardScreenPreview() {
    PianoAppTheme {
        DashboardScreen(
            viewModel = DashboardViewModel()
        )
    }
}