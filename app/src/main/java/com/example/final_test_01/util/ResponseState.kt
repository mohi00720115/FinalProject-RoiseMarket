package com.example.final_test_01.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

/**
 * مدیریت ارورهای برنامه
 */
fun <T> Flow<T>.asResponseState(): Flow<ResponseState<T>> {
    return this
        .map<T, ResponseState<T>> {
            ResponseState.Success(it)
        }
        .catch {
            emit(ResponseState.Error(it))
        }
        .onStart {
            emit(ResponseState.Loading)
        }
}

sealed interface ResponseState<out T> {
    data class Success<T>(val data: T) : ResponseState<T>
    object Loading : ResponseState<Nothing>
    data class Error(val exception: Throwable) : ResponseState<Nothing>
}