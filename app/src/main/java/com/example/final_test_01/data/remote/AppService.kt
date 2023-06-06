package com.example.final_test_01.data.remote

import com.example.final_test_01.data.model.product_category_dto.ProductsCategoryUiState
import com.example.final_test_01.data.model.product_category_dto.ProductsCategoryUiStateItem
import com.example.final_test_01.data.model.product_dto.ProductsUiState
import com.example.final_test_01.data.model.product_dto.ProductsUiStateItem
import retrofit2.http.GET
import retrofit2.http.Query

interface AppService {

    /**
     * گرفتن ID های هر محصول
     */
    @GET("products")
    suspend fun getIdItemsProducts(
        @Query("include") id: Int
    ): List<ProductsUiStateItem>

    /**
     * جدید ترین محصولات
     */
    @GET("products/")
    suspend fun getNewestProducts(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10,
        @Query("orderby") orderBy: String = "date"
    ): ProductsUiState

    /**
     * پر بازدید ترین محصولات
     */
    @GET("products/")
    suspend fun getMostVisitedProducts(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10,
        @Query("orderby") orderBy: String = "popularity"
    ): ProductsUiState

    /**
     * بهترین محصولات
     */
    @GET("products/")
    suspend fun getTopRatedProducts(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10,
        @Query("orderby") orderBy: String = "rating"
    ): ProductsUiState

    //------------------------------------------------------------------------------------------------//
    @GET("products/categories")
    suspend fun getProductsCategories(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10,
    ): List<ProductsCategoryUiStateItem>

    @GET("products/")
    suspend fun getProductsByCategories(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10,
        @Query("category") category: String
    ): List<ProductsUiStateItem>

}