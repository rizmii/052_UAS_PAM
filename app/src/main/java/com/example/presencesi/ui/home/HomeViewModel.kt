package com.example.presencesi.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.presencesi.data.RepositoryPresensi
import com.example.presencesi.model.Presensi
import com.example.presencesi.ui.HomeUIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

sealed class PresensiUIState {
    data class Success(val presensi: Flow<List<Presensi>>) : PresensiUIState()
    object Error : PresensiUIState()
    object Loading : PresensiUIState()
}

class HomeViewModel(private val repositoryPresensi: RepositoryPresensi) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val homeUIState: StateFlow<HomeUIState> = repositoryPresensi.getAll()
        .filterNotNull()
        .map {
            HomeUIState (listPresensi = it.toList(), it.size ) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUIState()

        )

}
