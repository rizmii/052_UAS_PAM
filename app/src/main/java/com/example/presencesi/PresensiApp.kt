package com.example.presencesi

import android.app.Application
import com.example.presencesi.data.AppContainer
import com.example.presencesi.data.ContainerPresensi

class PresensiApp: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()

        container = ContainerPresensi()
    }
}