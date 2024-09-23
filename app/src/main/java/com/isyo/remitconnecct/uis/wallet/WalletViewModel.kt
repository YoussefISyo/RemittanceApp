package com.isyo.remitconnecct.uis.wallet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isyo.remitconnecct.data.RecipientRepository
import com.isyo.remitconnecct.data.WalletRepository
import com.isyo.remitconnecct.model.User
import com.isyo.remitconnecct.model.Wallet
import com.isyo.remitconnecct.uis.recipient.RecipientsUiState
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

sealed interface WalletsUiState {
    data class Success(val wallets: Response<List<Wallet>>) : WalletsUiState
    object Error : WalletsUiState
    object Loading : WalletsUiState
}

class WalletViewModel(private val walletRepository: WalletRepository) : ViewModel() {

    var walletsUiState: WalletsUiState by mutableStateOf(WalletsUiState.Loading)
        private set

    init {
        getWallets()
    }


    private fun getWallets() {
        viewModelScope.launch {
            walletsUiState = WalletsUiState.Loading
            walletsUiState = try {
                WalletsUiState.Success(walletRepository.getWallets())
            } catch (e: IOException) {
                WalletsUiState.Error
            } catch (e: HttpException) {
                WalletsUiState.Error
            }
        }
    }
}