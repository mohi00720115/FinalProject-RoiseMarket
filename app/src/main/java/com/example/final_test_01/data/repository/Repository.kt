package com.example.final_test_01.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.example.final_test_01.data.local.ICartDao
import com.example.final_test_01.data.local.LineItemEntity
import com.example.final_test_01.data.model.dto.customer.CustomerDto
import com.example.final_test_01.data.model.dto.order.OrderDto
import com.example.final_test_01.data.model.dto.product_category.ProductsCategoryItemsDto
import com.example.final_test_01.data.model.dto.product.ProductsDto
import com.example.final_test_01.data.model.dto.product.ProductsItemsDto
import com.example.final_test_01.data.model.ui.MyCustomerItem
import com.example.final_test_01.data.remote.AppService
import com.example.final_test_01.mapper.customerItemToCustomerDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.system.measureTimeMillis

class Repository @Inject constructor(
    private val appService: AppService,
    private val cartDao: ICartDao
) {

    suspend fun getItemsIdsProducts(id: Int): Flow<List<ProductsItemsDto>> {
        return flow {
            emit(appService.getItemsIdsProducts(id))
            Log.e(TAG, "appService: $id")
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

    suspend fun getCategoryForSearch(
        search: String,
        orderby: String
    ): Flow<List<ProductsItemsDto>> {
        return flow {
            emit(appService.getCategoryForSearch(search, orderby))
        }
    }

    suspend fun createCustomer(
//        email: String,
        dto: CustomerDto
    ): Flow<CustomerDto> {
        return flow {
//            emit(appService.createCustomer(customerItemToCustomerDto(MyCustomerItem.empty.copy(email = email))))
            emit(appService.createCustomer(dto))
        }
    }

    suspend fun createOrders(order: OrderDto): Flow<OrderDto> {
        return flow {
            emit(appService.createOrders(order))
        }
    }

    //---------------------------------------- Data Base -----------------------------------------//

    suspend fun insert(lineItemEntity: LineItemEntity) {
        cartDao.insert(lineItemEntity)
    }

    suspend fun update(lineItemEntity: LineItemEntity) {
        cartDao.update(lineItemEntity)
    }

    suspend fun delete(productId: Int) {
        cartDao.delete(productId)
    }

    fun getAllProduct(): Flow<List<LineItemEntity>> {
        return flow {
            emit(cartDao.getAllProduct())
        }
    }

}
