package com.isyo.remitconnecct.app

import android.app.Application
import com.isyo.remitconnecct.uis.TransferViewModel

class RemitConnectApp: Application() {

    lateinit var transferViewModel: TransferViewModel

    override fun onCreate() {
        super.onCreate()

        transferViewModel = TransferViewModel(application = this)
    }
}