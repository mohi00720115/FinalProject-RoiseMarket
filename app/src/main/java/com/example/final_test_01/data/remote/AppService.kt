package com.example.final_test_01.data.remote

import com.example.final_test_01.data.model.product_category_dto.ProductsCategoryItemsDto
import com.example.final_test_01.data.model.product_dto.ProductsDto
import com.example.final_test_01.data.model.product_dto.ProductsItemsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface AppService {

    /**
     * گرفتن ID های هر محصول
     */
    @GET("products")
    suspend fun getItemsIdsProducts(
        @Query("include") id: Int
    ): List<ProductsItemsDto>

    /**
     * جدید ترین محصولات
     */
    @GET("products/")
    suspend fun getNewestProducts(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10,
        @Query("orderby") orderBy: String = "date"
    ): ProductsDto

    /**
     * پر بازدید ترین محصولات
     */
    @GET("products/")
    suspend fun getMostVisitedProducts(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10,
        @Query("orderby") orderBy: String = "popularity"
    ): ProductsDto

    /**
     * بهترین محصولات
     */
    @GET("products/")
    suspend fun getTopRatedProducts(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10,
        @Query("orderby") orderBy: String = "rating"
    ): ProductsDto

//------------------------------------------------------------------------------------------------//
    /**
     * گرفتن تمام کتگوری ها بصورت یک لیست
     */
    @GET("products/categories")
    suspend fun getAllCategories(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10,
    ): List<ProductsCategoryItemsDto>

    /**
     * گرفتن ID های هر کتگوری
     */
    @GET("products/")
    suspend fun getCategoriesByIds(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10,
        @Query("category") category: String
    ): List<ProductsItemsDto>

}