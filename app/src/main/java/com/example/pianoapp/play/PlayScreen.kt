package com.example.pianoapp.play

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pianoapp.R
import com.example.pianoapp.keyboard.KeyboardPartType
import com.example.pianoapp.keyboard.KeyboardViewModel
import com.example.pianoapp.keyboard.ui.KeyboardComponentContent
import com.example.pianoapp.notation.data.NoteDuration
import com.example.pianoapp.notation.data.TailType
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlayScreen() {

   val keyboardViewModel: KeyboardViewModel = koinViewModel()
   val keyboardState by keyboardViewModel.uiState.collectAsState()

   val playScreenViewModel: PlayScreenViewModel = koinViewModel()
   val playScreenUiState by playScreenViewModel.uiState.collectAsState()

   PlayScreenContent(
      keyboardState.whiteKeysRenderData,
      keyboardState.blackKeysRenderData,
      playScreenUiState.beatsToGenerate
   )
}


@Composable
fun PlayScreenContent(
   whiteKeysRenderData: List<KeyboardPartType>,
   blackKeysRenderData: List<KeyboardPartType>,
   beatsToGenerate: List<BeatToGenerate> = emptyList()
) {
   Column(
      Modifier
         .background(Color(0xFF141414))
         .fillMaxSize()
   ) {
      Box(
         Modifier
            .fillMaxWidth()
            .weight(3F)
      ) {
         StaffHolder()
         Row {
            KeyAndMeterHolder()
            LazyRow{
               items(beatsToGenerate){
                  Spacer(Modifier.width(30.dp))
                  BeatHolder(it)
                  BarLine()
               }
            }
         }
      }
      Box(
         Modifier
            .fillMaxWidth()
            .weight(1F)
      ) {
         KeyboardComponentContent(whiteKeysRenderData, blackKeysRenderData)
      }
   }
}

@Composable
fun NoteHolder(
   topOffset: Float,
   bottomOffset: Float,
   noteDuration: NoteDuration,
   tailType: TailType,
   rightOffset: Int,
) {
   Row(Modifier.fillMaxHeight()) {
      Box {
         Column(
         ) {
            Spacer(Modifier.weight(topOffset.takeIf { it > 0 } ?: Float.MIN_VALUE))
            Icon(
               painter = painterResource(noteDuration.iconRes),
               contentDescription = "",
               modifier = Modifier.weight(1F),
               tint = Color.White
            )
            Spacer(Modifier.weight(bottomOffset.takeIf { it > 0 } ?: Float.MIN_VALUE))
         }

         if(tailType == TailType.EIGHT_TAIL_BEFORE){
            Column(
            ) {
               Spacer(Modifier.weight((topOffset + 1).takeIf { it > 0 } ?: Float.MIN_VALUE))
               Icon(
                  painter = painterResource(R.drawable.osemka2),
                  contentDescription = "",
                  modifier = Modifier.weight(3F),
                  tint = Color.White
               )
               Spacer(Modifier.weight((bottomOffset - 3).takeIf { it > 0 } ?: Float.MIN_VALUE))
            }
         }

         if (tailType == TailType.STRAIGHT_TAIL_BEFORE || tailType == TailType.EIGHT_TAIL_BEFORE) Tail(modifier = Modifier.align(Alignment.CenterStart), topOffset = topOffset + 0.2F, bottomOffset = bottomOffset - 3F)
         if (tailType == TailType.STRAIGHT_TAIL_AFTER || tailType == TailType.EIGHT_TAIL_AFTER) Tail(modifier = Modifier.align(Alignment.CenterEnd), topOffset = topOffset - 3F, bottomOffset = bottomOffset + 0.2F)
      }

      if(tailType == TailType.EIGHT_TAIL_AFTER){
         Column{
            Spacer(Modifier.weight((topOffset - 3F).takeIf { it > 0 } ?: Float.MIN_VALUE))
            Icon(
               painter = painterResource(R.drawable.ogonek),
               contentDescription = "",
               modifier = Modifier.weight(3F),
               tint = Color.White)
            Spacer(Modifier.weight((bottomOffset + 1F).takeIf { it > 0 } ?: Float.MIN_VALUE))
         }
      }

      Spacer(Modifier.width(
         rightOffset.dp
      ))

   }
}

@Composable
fun Tail(
   modifier: Modifier,
   topOffset: Float,
   bottomOffset: Float
) {
   Column(
      modifier
         .width(1.dp)
         .fillMaxHeight()
   ) {
      Spacer(Modifier.weight((topOffset).takeIf { it > 0 } ?: Float.MIN_VALUE))
      Column(
         Modifier
            .weight(3F)
            .width(1.dp)
            .background(Color.White)
      ) {}
      Spacer(Modifier.weight(bottomOffset.takeIf { it > 0 } ?: Float.MIN_VALUE))
   }
}

@Composable
fun BarLine(
) {
   Column(Modifier.fillMaxWidth()) {
      Spacer(Modifier.weight(5F))
      Column(
         Modifier
            .width(1.dp)
            .weight(14.3F)
            .background(Color.White)
      ) {}
      Spacer(Modifier.weight(5F))
   }
}

@Composable
fun KeyAndMeterHolder() {
   Row(Modifier.fillMaxHeight()) {
      Spacer(Modifier.width(10.dp))
      Column(Modifier.fillMaxHeight()) {
         Spacer(Modifier.weight(4F))
         Icon(
            painter = painterResource(R.drawable.violin_key),
            contentDescription = "null",
            tint = Color.White,
            modifier = Modifier.weight(6.15F),
         )
         Spacer(Modifier.weight(5F))
         Icon(
            painter = painterResource(R.drawable.bass_key),
            contentDescription = "null",
            tint = Color.White,
            modifier = Modifier.weight(3.32F),
         )
         Spacer(Modifier.weight(5.83F))
      }
      Spacer(Modifier.width(15.dp))
      Column(Modifier.fillMaxHeight()) {
         Spacer(Modifier.weight(5F))
         Icon(
            painter = painterResource(R.drawable.ts34),
            contentDescription = "null",
            tint = Color.White,
            modifier = Modifier.weight(4.15F),
         )
         Spacer(Modifier.weight(6F))
         Icon(
            painter = painterResource(R.drawable.ts34),
            contentDescription = "null",
            tint = Color.White,
            modifier = Modifier.weight(4.15F),
         )
         Spacer(Modifier.weight(5F))
      }
      Spacer(Modifier.width(15.dp))
   }
}

@Composable
fun BeatHolder(
   beatToGenerate: BeatToGenerate
) {
      beatToGenerate.groupsToGenerate.forEach { groupToGenerate ->
         Box{
            groupToGenerate.notes.forEach {
               NoteHolder(
                  it.heightTopOffset,
                  it.heightBottomOffset,
                  it.noteDuration,
                  it.tailType,
                  groupToGenerate.rightOffset
               )
            }
         }
      }
}

@Composable
fun StaffHolder() {
   Column {
      Row(
         Modifier
            .fillMaxWidth()
            .weight(5F)
      ) {}
      repeat(4) {
         Row(
            Modifier
               .fillMaxWidth()
               .background(Color.White)
               .weight(0.04F)
         ) {}
         Row(
            Modifier
               .fillMaxWidth()
               .weight(1F)
         ) {}
      }
      Row(
         Modifier
            .fillMaxWidth()
            .background(Color.White)
            .weight(0.04F)
      ) {}
      Row(
         Modifier
            .fillMaxWidth()
            .weight(6F)
      ) {}
      repeat(4) {
         Row(
            Modifier
               .fillMaxWidth()
               .background(Color.White)
               .weight(0.04F)
         ) {}
         Row(
            Modifier
               .fillMaxWidth()
               .weight(1F)
         ) {}
      }
      Row(
         Modifier
            .fillMaxWidth()
            .background(Color.White)
            .weight(0.04F)
      ) {}
      Row(
         Modifier
            .fillMaxWidth()
            .weight(5F)
      ) {}
   }
}

val NoteDuration.iconRes: Int
   get() = when (this) {
      NoteDuration.WHOLE_NOTE -> R.drawable.note44
      NoteDuration.HALF_NOTE -> R.drawable.halfnote1
      NoteDuration.QUARTER_NOTE -> R.drawable.quarternote1
      NoteDuration.EIGHT_NOTE -> R.drawable.quarternote1
      NoteDuration.SIXTEENTH_NOTE -> R.drawable.quarternote1
   }

val PLAY_SCREEN_ROUTE = "play"