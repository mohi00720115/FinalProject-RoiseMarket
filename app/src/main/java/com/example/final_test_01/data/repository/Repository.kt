package com.example.final_test_01.data.repository

import com.example.final_test_01.data.model.dto.customer.CustomerDto
import com.example.final_test_01.data.model.dto.product_category.ProductsCategoryItemsDto
import com.example.final_test_01.data.model.dto.product.ProductsDto
import com.example.final_test_01.data.model.dto.product.ProductsItemsDto
import com.example.final_test_01.data.remote.AppService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Repository @Inject constructor(private val appService: AppService) {

    suspend fun getItemsIdsProducts(id: Int): Flow<List<ProductsItemsDto>> {
        return flow {
            emit(appService.getItemsIdsProducts(id))
        }
    }

    suspend fun getNewestProducts(page: Int, perPage: Int): Flow<ProductsDto> {
        return flow {
            emit(appService.getNewestProducts(page, perPage))
        }
    }

    suspend fun getMostVisitedProducts(page: Int, perPage: Int): Flow<ProductsDto> {
        return flow {
            emit(appService.getMostVisitedProducts(page, perPage))
        }
    }

    suspend fun getTopRatedProducts(page: Int, perPage: Int): Flow<ProductsDto> {
        return flow {
            emit(appService.getTopRatedProducts(page, perPage))
        }
    }

    suspend fun getAllCategories(page: Int, perPage: Int): Flow<List<ProductsCategoryItemsDto>> {
        return flow {
            emit(appService.getAllCategories(page, perPage))
        }
    }

    suspend fun getCategoriesByIds(
        page: Int,
        perPage: Int,
        category: String
    ): Flow<List<ProductsItemsDto>> {
        return flow {
            emit(appService.getCategoriesByIds(page, perPage, category))
        }
    }

    suspend fun getIdsCategoryForViewPager(
        page: Int,
        perPage: Int
    ): Flow<List<ProductsItemsDto>> {
        return flow {
            emit(appService.getIdsCategoryForViewPager(page, perPage))
        }
    }

    suspend fun getAllProductsForSearch(
        search: String,
        orderby: String
    ): Flow<List<ProductsItemsDto>> {
        return flow {
            emit(appService.getAllProductsForSearch(search, orderby))
        }
    }

    suspend fun createCustomer(customer: CustomerDto): Flow<CustomerDto> {
        return flow {
            emit(appService.createCustomer(customer))
        }
    }
}
