package com.example.presencesi.data

data class Presensi(
    val NIM : String,
    val nama : String,
    val tanggal : String,
    val keterangan : String
){
    constructor(): this("","","","")
}