package com.example.pianoapp.dashboard.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pianoapp.R
import com.example.pianoapp.dashboard.ui.data.MenuItemData
import com.example.pianoapp.dashboard.ui.data.MenuItemDataType
import com.example.pianoapp.dashboard.ui.data.toNavRoute
import com.example.pianoapp.ui.theme.PianoAppTheme

@Composable
fun DashboardScreen(
    navController: NavController
){
    DashboardScreenContent(
        onMenuItemChosen = {
            navController.navigate(it.toNavRoute())
        }
    )
}

@Composable
fun DashboardScreenContent(
    onMenuItemChosen: (MenuItemDataType) -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(Color(0xFF050505))
                .padding(30.dp)
        ) {
            DashboardTitleText(userName = "Steve")
            Spacer(Modifier.height(30.dp))
            DashboardPlayContainer()
            Spacer(Modifier.height(30.dp))
            DashboardRowMenuCarousel(
                itemsData = itemsData,
                onMenuItemChosen = onMenuItemChosen
            )
        }
        Column(
            Modifier
                .fillMaxSize()
                .background(Color(0xFF141414))
                .padding(20.dp)
        ) {

        }
    }

}

@Composable
fun DashboardTitleText(
    userName: String
) {
    Text(
        text = "Hello, $userName!", color = Color(0xFFededed), fontFamily = FontFamily(
            Font(R.font.poppins_medium)
        ), fontSize = 32.sp, modifier = Modifier.padding(start = 15.dp)
    )
}

@Composable
fun DashboardPlayContainer() {
    Column(
        Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(
                color = Color(0xFF7351fc), shape = RoundedCornerShape(20.dp)
            )
    ) {

    }
}

@Composable
fun DashboardRowMenuCarousel(
    itemsData: List<MenuItemData>,
    onMenuItemChosen: (MenuItemDataType) -> Unit
) {
    LazyRow {
        items(itemsData) {
            DashboardMenuComponent(itemText = it.text,
                itemIconResource = it.iconResource,
                onClick = { onMenuItemChosen(it.type) })
            Spacer(Modifier.width(20.dp))
        }
    }
}

@Composable
fun DashboardMenuComponent(
    itemText: String, itemIconResource: Int, onClick: () -> Unit
) {
    Column(
        Modifier
            .size(100.dp)
            .background(
                color = Color(0xFF050505), shape = RoundedCornerShape(30.dp)
            )
            .border(1.dp, Color(0xFF9e9e9e), RoundedCornerShape(30.dp))
            .padding(10.dp)
            .clickable { onClick() }

    ) {
        Spacer(Modifier.weight(1F))
        Row(Modifier.fillMaxWidth()) {
            Spacer(Modifier.weight(1F))
            Icon(
                painter = painterResource(itemIconResource),
                tint = Color.White,
                contentDescription = "",
                modifier = Modifier.size(30.dp)
            )
            Spacer(Modifier.weight(1F))
        }
        Spacer(Modifier.weight(1F))
        Text(
            color = Color(0xFFededed),
            fontFamily = FontFamily(
                Font(R.font.poppins_light)
            ),
            text = itemText,
            fontSize = 11.sp,
            lineHeight = 11.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.weight(1F))
    }
}

@Composable
@Preview(widthDp = 500, heightDp = 1000)
fun DashboardPreviewBigger() {
    PianoAppTheme {
        DashboardScreenContent(
            {}
        )
    }
}

@Composable
@Preview(widthDp = 400, heightDp = 800)
fun DashboardPreviewBigger2() {
    PianoAppTheme {
        DashboardScreenContent(
            {}
        )
    }
}

@Composable
@Preview
fun DashboardMenuComponentPreview() {
    PianoAppTheme {
        DashboardMenuComponent(itemText = "Connect piano",
            itemIconResource = R.drawable.usb_svgrepo_com_2,
            onClick = {})
    }
}

@Composable
@Preview
fun DashboardRowMenuCarouselPreview() {
    PianoAppTheme {
        DashboardRowMenuCarousel(
            itemsData,
            {}
        )
    }
}

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

val DASHBOARD_SCREEN_ROUTE = "dashboard"