package com.isyo.remitconnecct.data

import com.isyo.remitconnecct.domain.RetrofitService
import com.isyo.remitconnecct.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class RecipientRepository(private val apiService: RetrofitService) {

    suspend fun getRecipients(): Response<List<User>> {
        return withContext(Dispatchers.IO) {
            apiService.getRecipients()
        }
    }
}