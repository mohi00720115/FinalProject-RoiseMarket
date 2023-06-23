package com.example.final_test_01.data.remote

import com.example.final_test_01.data.model.dto.customer.CustomerDto
import com.example.final_test_01.data.model.dto.order.OrderDto
import com.example.final_test_01.data.model.dto.product_category.ProductsCategoryItemsDto
import com.example.final_test_01.data.model.dto.product.ProductsDto
import com.example.final_test_01.data.model.dto.product.ProductsItemsDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
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
        @Query("per_page") perPage: Int = 15,
        @Query("orderby") orderBy: String = "date"
    ): ProductsDto

    /**
     * پر بازدید ترین محصولات
     */
    @GET("products/")
    suspend fun getMostVisitedProducts(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 15,
        @Query("orderby") orderBy: String = "popularity"
    ): ProductsDto

    /**
     * بهترین محصولات
     */
    @GET("products/")
    suspend fun getTopRatedProducts(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 15,
        @Query("orderby") orderBy: String = "rating"
    ): ProductsDto

//------------------------------------------------------------------------------------------------//
    /**
     * گرفتن تمام کتگوری ها بصورت یک لیست
     */
    @GET("products/categories")
    suspend fun getAllCategories(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 15,
    ): List<ProductsCategoryItemsDto>

    /**
     * گرفتن ID های هر کتگوری
     */
    @GET("products/")
    suspend fun getCategoriesByIds(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 15,
        @Query("category") category: String
    ): List<ProductsItemsDto>

    /**
     * گرفتن ID کتگوری ها و ست کردن عکسشون در ویو پیجر
     */
    @GET("products/")
    suspend fun getIdsCategoryForViewPager(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 15,
        @Query("category") category: String = "119"
    ): List<ProductsItemsDto>

    /**
     * گرفتن همه ی محصولات برای سرچ
     */
    @GET("products")
    suspend fun getAllProductsForSearch(
        @Query("search") search: String,
        @Query("orderby") orderBy: String,
    ): List<ProductsItemsDto>

    //--------------------------------------------------------------------------------------------//

    /**
     * گرفتن هر کتگوری و سرچ روی آن ها
     */
    @GET("products/categories")
    suspend fun getCategoryForSearch(
        @Query("search") search: String,
        @Query("orderby") orderBy: String,
//        @Query("category") category: String = "119",
    ): List<ProductsItemsDto>

    /**
     * برای ثبت نام کاربر (مشتری) در سایت
     */
    @POST("customers")
    @Headers("Content-Type: application/json")
    suspend fun createCustomer(@Body customerDto: CustomerDto): CustomerDto

    @POST("orders")
    suspend fun createOrders(@Body orderDto: OrderDto): OrderDto


    /**
     * //    private object Route {
    //        private const val PREFIX = "/wp-json/wc/v3"
    //        const val PRODUCT_DETAILS = "$PREFIX/products/{id}"
    //        const val PRODUCTS = "$PREFIX/products"
    //        const val PRODUCTS_CATEGORIES = "$PREFIX/products/categories"
    //
    //        /* customer */
    //        const val CUSTOMERS = "$PREFIX/customers"
    //        const val CUSTOMERS_ID = "$PREFIX/customers/{id}"
    //
    //        /* Orders */
    //        const val ORDERS = "$PREFIX/orders"
    //        const val ORDERS_ID = "$PREFIX/orders/{id}"
    //
    //        /* Reviews */
    //        const val REVIEWS = "$PREFIX/products/reviews"
    //
    //        /* Coupons */
    //        const val COUPONS = "$PREFIX/coupons"
    //    }
    //    private object PARAMS {
    //        const val PRODUCT_ID = "id"
    //        const val PAGE = "page"
    //        const val PER_PAGE = "per_page"
    //    }

     */

}