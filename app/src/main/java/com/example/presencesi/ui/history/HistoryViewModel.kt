package com.example.presencesi.ui.history

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.presencesi.data.RepositoryPresensi
import com.example.presencesi.ui.DetailUIState
import com.example.presencesi.ui.toDetailPresensi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HistoryViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: RepositoryPresensi
) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val presensiId: String = checkNotNull(savedStateHandle[DetailDestination.presensiId])

    val uiState: StateFlow<DetailUIState> =
        repository.getAbsenById(presensiId)
            .filterNotNull()
            .map {
                DetailUIState(addEvent = it.toDetailPresensi())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = DetailUIState()
            )

    suspend fun deletePresensi() {
        repository.delete(presensiId)

    }


}