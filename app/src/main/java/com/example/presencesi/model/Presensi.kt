package com.example.presencesi.model

data class Presensi(
    val id : String,
    val nama : String,
    val waktu : String
){
    constructor(): this("","","")
}

