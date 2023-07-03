package com.example.final_test_01.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.example.final_test_01.data.local.ICartDao
import com.example.final_test_01.data.model.dto.customer.CustomerDto
import com.example.final_test_01.data.model.dto.order.OrderDto
import com.example.final_test_01.data.model.dto.product.ProductsDto
import com.example.final_test_01.data.model.dto.product.ProductsItemsDto
import com.example.final_test_01.data.model.dto.product_category.ProductsCategoryItemsDto
import com.example.final_test_01.data.model.dto.review.ReviewDto
import com.example.final_test_01.data.remote.AppService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

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

    suspend fun getCustomersByEmail(email: String): Flow<List<CustomerDto>> {
        return flow {
            emit(appService.getCustomersByEmail(email))
        }
    }

    suspend fun createOrders(order: OrderDto): Flow<OrderDto> {
        return flow {
            emit(appService.createOrders(order))
        }
    }

    suspend fun getOrdersByEmail(searchEmail: String): Flow<List<OrderDto>> {
        return flow {
            emit(appService.getOrdersByEmail(searchEmail))
        }
    }

    suspend fun putUpdateOrder(id: Int, customerOrder: OrderDto): Flow<OrderDto> {
        return flow {
            emit(appService.putUpdateOrder(id, customerOrder))
        }
    }

    suspend fun getOrderById(id: Int): Flow<OrderDto> {
        return flow {
            emit(appService.getOrderById(id))
        }
    }

    suspend fun getProductReviewList(id: Int): Flow<List<ReviewDto>> {
        return flow {
            emit(appService.getProductReviewList(id))
        }
    }

    suspend fun putUpdateReview(id: Int): Flow<ReviewDto> {
        return flow {
            emit(appService.putUpdateReview(id))
        }
    }

    suspend fun deleteReview(id: Int): Flow<ReviewDto> {
        return flow {
            emit(appService.deleteReview(id))
        }
    }

}
