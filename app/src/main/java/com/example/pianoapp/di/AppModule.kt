package com.example.pianoapp.di

import android.content.Context
import android.media.midi.MidiDeviceInfo
import android.media.midi.MidiManager
import com.example.pianoapp.connection.ui.ConnectPianoViewModel
import com.example.pianoapp.dashboard.ui.DashboardViewModel
import com.example.pianoapp.usecase.connection.ConnectDeviceUseCase
import com.example.pianoapp.usecase.connection.KeyboardSignalReceiver
import com.example.pianoapp.usecase.connection.MIDIParser
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    single<MidiManager>{
        androidContext().getSystemService(Context.MIDI_SERVICE) as MidiManager
    }
    singleOf(::MIDIParser)
    singleOf(::KeyboardSignalReceiver)
    single{
        ConnectDeviceUseCase(
            keyboardSignalReceiver = get(),
            midiManager = get(),
        )
    }
    singleOf(::ConnectDeviceUseCase)

    viewModelOf(::ConnectPianoViewModel)
    viewModelOf(::DashboardViewModel)
}