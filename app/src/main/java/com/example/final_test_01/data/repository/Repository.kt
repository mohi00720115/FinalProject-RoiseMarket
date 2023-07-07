package com.example.final_test_01.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.example.final_test_01.data.model.dto.customer.CustomerDto
import com.example.final_test_01.data.model.dto.order.OrderDto
import com.example.final_test_01.data.model.dto.product.ProductsDto
import com.example.final_test_01.data.model.dto.product.ProductsItemsDto
import com.example.final_test_01.data.model.dto.product_category.ProductsCategoryItemsDto
import com.example.final_test_01.data.model.dto.review.ReviewDto
import com.example.final_test_01.data.remote.AppService
import com.example.final_test_01.util.Const.CUSTOMER_EMAIL
import com.example.final_test_01.util.Const.ORDERS_ID
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val appService: AppService,
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

    suspend fun putUpdateOrder(id: Int, customerOrder: OrderDto): Flow<OrderDto> {
        return flow {
            emit(appService.putUpdateOrder(id, customerOrder))
        }
    }

    suspend fun createReview(reviewDto: ReviewDto): Flow<ReviewDto> {
        return flow {
            emit(appService.createReview(reviewDto))
        }
    }

    suspend fun getReviewById(reviewId: Int): Flow<ReviewDto> {
        return flow {
            emit(appService.getReviewById(reviewId))
        }
    }

    suspend fun updateReview(reviewId: Int, reviewDto: ReviewDto): Flow<ReviewDto> {
        return flow {
            emit(appService.updateReview(reviewId, reviewDto))
        }
    }

    suspend fun getOrderById(orderId: Int): Flow<OrderDto> {
        return flow {
            emit(appService.getOrderById(orderId))
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

    /**
     * چک کردن تمام شروط ثبت نام و ایجاد کاربر و ساخت و نمایش سفارشاتش
     */
    suspend fun checkLogin(customerDto: CustomerDto, ordDto: OrderDto): Flow<CustomerDto> {
        return flow {
            if (appService.getCustomersByEmail(customerDto.email).isEmpty()) {
                val customer = appService.createCustomer(customerDto)
                CUSTOMER_EMAIL = customer.email
                ORDERS_ID = appService.createOrders(ordDto).id!!
                emit(customer)
            } else {
                val customer = appService.getCustomersByEmail(customerDto.email).first()
                CUSTOMER_EMAIL = customer.email
                if (appService.getOrdersByEmail(customerDto.email).isEmpty()) {
                    ORDERS_ID = appService.createOrders(ordDto).id!!
                } else {
                    ORDERS_ID = appService.getOrdersByEmail(customerDto.email).first().id!!
                }
                emit(customer)
            }
        }
    }

}
