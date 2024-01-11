package com.example.presencesi.ui

import com.example.presencesi.model.Presensi

data class UIStatePresensi(
    val detailPresensi: DetailPresensi = DetailPresensi(),
    val isEntryValid: Boolean = false
)

data class DetailPresensi(
    val NIM : String = "",
    val nama : String = "",
    val tanggal : String = "",
    val keterangan : String = ""
)

data class AddUIState(
    val addEvent: AddEvent = AddEvent(),
)

data class AddEvent(
    val NIM: String = "",
    val nama: String = "",
    val tanggal: String = "",
    val keterangan: String = "",
)

fun AddEvent.toPresensi() = Presensi(
    NIM = NIM,
    nama = nama,
    tanggal = tanggal,
    keterangan = keterangan
)

data class DetailUIState(
    val addEvent: AddEvent = AddEvent(),
)

fun Presensi.toDetailPresensi(): AddEvent =
    AddEvent(
        NIM = NIM,
        nama = nama,
        tanggal = tanggal,
        keterangan = keterangan
    )

fun Presensi.toUIStatePresensi(): AddUIState = AddUIState(
    addEvent = this.toDetailPresensi()
)

data class HomeUIState(
    val listPresensi: List<Presensi> = listOf(),
    val dataLength: Int = 0
)