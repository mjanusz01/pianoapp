package com.example.pianoapp.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pianoapp.R
import com.example.pianoapp.ui.theme.PianoAppTheme

@Composable
fun Tile(
    onClick: () -> Unit,
    iconResource: Int,
    headlineText: String,
    backgroundColor: Color,
) {
    Row(
        modifier = Modifier
            .background(color = backgroundColor, shape = RoundedCornerShape(15.dp))
            .border(
                width = 1.dp,
                color = Color(0xFF572a0f),
                shape = RoundedCornerShape(15.dp)
            )
            .padding(20.dp)
            .clickable {
                onClick()
            },
    ) {
        Column() {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(iconResource),
                    contentDescription = "",
                    tint = Color(0xFF1a1a1a),
                    modifier = Modifier.size(60.dp)
                )
            }
            Spacer(Modifier.height(12.dp))
            Text(
                text = headlineText,
                fontFamily = FontFamily(
                    Font(R.font.poppins_medium)
                ),
                fontSize = 20.sp,
                color = Color(0xFF1a1a1a),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
@Preview
fun ButtonPreview() {
    PianoAppTheme {
        Tile(
            onClick = { },
            iconResource = R.drawable.bluetooth_on_svgrepo_com,
            headlineText = "Bluetooth",
            backgroundColor = Color(0xFFb4decf),
        )
    }
}