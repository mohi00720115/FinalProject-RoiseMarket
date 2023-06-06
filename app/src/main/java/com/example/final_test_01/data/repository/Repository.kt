package com.example.final_test_01.data.repository

import com.example.final_test_01.data.model.product_category_dto.ProductsCategoryItemsDto
import com.example.final_test_01.data.model.product_dto.ProductsDto
import com.example.final_test_01.data.model.product_dto.ProductsItemsDto
import com.example.final_test_01.data.remote.AppService
import javax.inject.Inject

class Repository @Inject constructor(private val appService: AppService) {

    suspend fun getItemsIdsProducts(id: Int): List<ProductsItemsDto> {
        return appService.getItemsIdsProducts(id)
    }

    suspend fun getNewestProducts(page: Int, perPage: Int): ProductsDto {
        return appService.getNewestProducts(page, perPage)
    }

    suspend fun getMostVisitedProducts(page: Int, perPage: Int): ProductsDto {
        return appService.getMostVisitedProducts(page, perPage)
    }

    suspend fun getTopRatedProducts(page: Int, perPage: Int): ProductsDto {
        return appService.getTopRatedProducts(page, perPage)
    }

    suspend fun getAllCategories(page: Int, perPage: Int): List<ProductsCategoryItemsDto> {
        return appService.getAllCategories(page, perPage)
    }

    suspend fun getCategoriesByIds(
        page: Int,
        perPage: Int,
        category: String
    ): List<ProductsItemsDto> {
        return appService.getCategoriesByIds(page, perPage, category)
    }
}
