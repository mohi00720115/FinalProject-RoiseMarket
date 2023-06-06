package com.example.final_test_01.data.repository

import com.example.final_test_01.data.model.product_category_dto.ProductsCategoryUiState
import com.example.final_test_01.data.model.product_category_dto.ProductsCategoryUiStateItem
import com.example.final_test_01.data.model.product_dto.ProductsUiState
import com.example.final_test_01.data.model.product_dto.ProductsUiStateItem
import com.example.final_test_01.data.remote.AppService
import javax.inject.Inject

class Repository @Inject constructor(private val appService: AppService) {

    suspend fun getIdItemsProducts(id: Int): List<ProductsUiStateItem> {
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

    suspend fun getProductsCategories(page: Int, perPage: Int): List<ProductsCategoryUiStateItem> {
        return appService.getProductsCategories(page, perPage)
    }

    suspend fun getProductsByCategories(
        page: Int,
        perPage: Int,
        category: String
    ): List<ProductsUiStateItem> {
        return appService.getProductsByCategories(page, perPage, category)
    }
}
