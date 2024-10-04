package com.example.pianoapp.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pianoapp.R
import com.example.pianoapp.ui.theme.PianoAppTheme

@Composable
fun TopBar(
    onBackClick: () -> Unit,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(Color(0xFF050505))
    ) {
        if (onBackClick != {}) {
            Icon(
                painter = painterResource(R.drawable.back_svgrepo_com),
                contentDescription = "",
                tint = Color(0xFFededed),
                modifier = Modifier.padding(5.dp).clickable { onBackClick() }
            )
        }
    }
}

@Composable
@Preview
fun ScaffoldPreview() {
    PianoAppTheme {
        TopBar(
            onBackClick = {},
        )
    }
}