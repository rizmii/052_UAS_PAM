package com.example.presencesi.ui.edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.presencesi.data.RepositoryPresensi
import com.example.presencesi.ui.AddEvent
import com.example.presencesi.ui.AddUIState
import com.example.presencesi.ui.toPresensi
import com.example.presencesi.ui.toUIStatePresensi
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: RepositoryPresensi
) : ViewModel() {

    var presensiUiState by mutableStateOf(AddUIState())
        private set

    private val presensiId: String = checkNotNull(savedStateHandle[EditDestination.presensiId])

    init {
        viewModelScope.launch {
            presensiUiState =
                repository.getAbsenById(presensiId)
                    .filterNotNull()
                    .first()
                    .toUIStatePresensi()
        }
    }

    fun updateUIState(addEvent: AddEvent) {
        presensiUiState = presensiUiState.copy(addEvent = addEvent)
    }

    suspend fun updateKontak() {
        repository.update(presensiUiState.addEvent.toPresensi())

    }
}