package com.example.final_test_01.data.repository

import com.example.final_test_01.data.model.product_dto.ProductsUiState
import com.example.final_test_01.data.model.product_dto.ProductsUiStateItem
import com.example.final_test_01.data.remote.AppService
import com.example.final_test_01.data.remote.DataClass
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Repository @Inject constructor(private val appService: AppService) {

    suspend fun getIdItemsProducts(id: Int): ProductsUiState {
        return appService.getIdItemsProducts(id)
    }

    suspend fun getNewestProducts(page: Int, perPage: Int): ProductsUiState {
        return appService.getNewestProducts(page, perPage)
    }

    suspend fun getMostVisitedProducts(page: Int, perPage: Int): ProductsUiState {
        return appService.getMostVisitedProducts(page, perPage)
    }

    suspend fun getTopRatedProducts(page: Int, perPage: Int): ProductsUiState {
        return appService.getTopRatedProducts(page, perPage)
    }
}
