package com.example.pianoapp.di

import android.content.Context
import android.media.midi.MidiManager
import com.example.pianoapp.connection.ui.ConnectPianoViewModel
import com.example.pianoapp.dashboard.ui.DashboardViewModel
import com.example.pianoapp.connection.usecase.connectdevice.ConnectDeviceUseCase
import com.example.pianoapp.connection.usecase.parser.KeyboardSignalReceiver
import com.example.pianoapp.connection.usecase.parser.ParseMIDIUseCase
import com.example.pianoapp.session.AppSession
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    single<MidiManager>{
        androidContext().getSystemService(Context.MIDI_SERVICE) as MidiManager
    }
    singleOf(::ParseMIDIUseCase)
    singleOf(::KeyboardSignalReceiver)
    singleOf(::AppSession)
    single{
        ConnectDeviceUseCase(
            keyboardSignalReceiver = get(),
            midiManager = get(),
            appSession = get()
        )
    }
    singleOf(::ConnectDeviceUseCase)

    viewModelOf(::ConnectPianoViewModel)
    viewModelOf(::DashboardViewModel)
}