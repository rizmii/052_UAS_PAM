package com.example.presencesi.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.presencesi.PresensiApp
import com.example.presencesi.ui.add.AddViewModel
import com.example.presencesi.ui.edit.EditViewModel
import com.example.presencesi.ui.history.HistoryViewModel
import com.example.presencesi.ui.home.HomeViewModel

fun CreationExtras.apkikasiPresensi(): PresensiApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PresensiApp)

object PenyediaViewModel {
    val Factory = viewModelFactory {

        initializer {
            AddViewModel(apkikasiPresensi().container.repositoryPresensi)
        }

        initializer {
            HomeViewModel(PresensiApp().container.repositoryPresensi)
        }

        initializer {
            HistoryViewModel(
                createSavedStateHandle(),
                PresensiApp().container.repositoryPresensi
            )
        }

        initializer {
            EditViewModel(
                createSavedStateHandle(),
                PresensiApp().container.repositoryPresensi
            )
        }
    }
}