package com.isyo.remitconnecct.uis.recipient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.isyo.remitconnecct.data.RecipientRepository

class RecipientViewModelFactory(private val repository: RecipientRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipientViewModel::class.java)) {
            return RecipientViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}