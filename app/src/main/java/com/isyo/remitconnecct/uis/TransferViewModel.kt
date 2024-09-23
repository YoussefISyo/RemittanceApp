package com.isyo.remitconnecct.uis

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TransferViewModel(application: Application) : AndroidViewModel(application){

    private val _name = MutableStateFlow("")
    var nameState: StateFlow<String> = _name

    private val _wallet = MutableStateFlow("")
    var walletState: StateFlow<String> = _wallet

    private val _amount = MutableStateFlow(0.0)
    var amountState: StateFlow<Double> = _amount

    private val _phone = MutableStateFlow("")
    var phoneState: StateFlow<String> = _phone

    fun updateName(name: String) {
        _name.value = name
    }

    fun updatePhone(phoneNumber: String) {
        _phone.value = phoneNumber
    }

    fun updateWallet(wallet: String) {
        _wallet.value = wallet
    }

    fun updateAmount(amount: Double) {
        _amount.value = amount
    }
}