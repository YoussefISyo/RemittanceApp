package com.isyo.remitconnecct.domain

import com.isyo.remitconnecct.model.User
import com.isyo.remitconnecct.model.Wallet
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

    @GET("recipients")
    suspend fun getRecipients() : Response<List<User>>

    @GET("wallets")
    suspend fun getWallets() : Response<List<Wallet>>

    companion object {
        private var retrofitService: RetrofitService? = null
        private const val BASE_URL = "https://my-json-server.typicode.com/MonecoHQ/fake-api/"

        fun getInstance() : RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }

    }
}