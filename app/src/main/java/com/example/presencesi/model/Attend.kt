package com.example.presencesi.model

data class Attend(
    val id : String,
    val nama : String,
    val waktu : String
){
    constructor(): this("","","")
}

