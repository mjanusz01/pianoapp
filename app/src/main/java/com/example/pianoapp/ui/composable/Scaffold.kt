package com.example.pianoapp.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.example.pianoapp.R
import com.example.pianoapp.ui.theme.PianoAppTheme

@Composable
fun AppScaffold(
    onBackClick: () -> Unit,
    scaffoldText: String? = null
) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(Color(0xFFf0d3c0))
    ) {
        if (onBackClick != {}) {
            Icon(
                painter = painterResource(R.drawable.back_svgrepo_com),
                contentDescription = "",
                tint = Color(0xFF572a0f),
                modifier = Modifier.padding(5.dp)
            )
        }
        Spacer(Modifier.weight(1F))
        if (scaffoldText != null) {
            Column {
                Spacer(Modifier.weight(1F))
                Text(
                    text = scaffoldText,
                    fontFamily = FontFamily(
                        Font(R.font.poppins_semibold)
                    ),
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.weight(1F))
            }
        }
        Spacer(Modifier.weight(1F))
        Spacer(Modifier.width(40.dp))
    }
}

@Composable
@Preview
fun ScaffoldPreview() {
    PianoAppTheme {
        AppScaffold(
            onBackClick = {},
            scaffoldText = "Scaffold text"
        )
    }
}