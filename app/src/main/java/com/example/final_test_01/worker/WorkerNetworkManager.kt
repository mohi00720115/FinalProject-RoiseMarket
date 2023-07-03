package com.example.final_test_01.worker

import com.example.final_test_01.util.Const.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WorkerNetworkManager {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: WorkerApi = retrofit.create(WorkerApi::class.java)
}