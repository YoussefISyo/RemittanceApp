package com.isyo.remitconnecct.data

import com.isyo.remitconnecct.domain.RetrofitService
import com.isyo.remitconnecct.model.Wallet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class WalletRepository(private val apiService: RetrofitService)  {

    suspend fun getWallets(): Response<List<Wallet>> {
        return withContext(Dispatchers.IO) {
            apiService.getWallets()
        }
    }
}