package com.example.presencesi.ui.add

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.presencesi.data.RepositoryPresensi
import com.example.presencesi.ui.AddEvent
import com.example.presencesi.ui.AddUIState
import com.example.presencesi.ui.toPresensi

class AddViewModel(private val repositoryPresensi: RepositoryPresensi) : ViewModel() {

    var addUIState by mutableStateOf(AddUIState())
        private set

    fun updateAddUIState(addEvent: AddEvent) {
        addUIState = AddUIState(addEvent = addEvent)
    }

    suspend fun addPresensi() {
        repositoryPresensi.save(addUIState.addEvent.toPresensi())
    }
}