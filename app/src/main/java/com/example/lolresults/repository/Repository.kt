package com.example.lolresults.repository

import com.example.lolresults.api.ApiResult
import java.io.IOException

open class Repository {

    open fun <T : Any> handleApiResult(result: ApiResult<T>?,
                                                onSuccessFunction: (data: T) -> Unit,
                                                onErrorFunction: (exception: Exception) -> Unit) {
        result?.let {
            when (it) {
                is ApiResult.Success -> onSuccessFunction.invoke(it.data)
                is ApiResult.Error -> onErrorFunction.invoke(it.exception)
            }
        }
    }

    open fun <T : Any> handleApiResultAndReturnData(result: ApiResult<T>?): T {
        result?.let {
            when (it) {
                is ApiResult.Success -> return it.data
                is ApiResult.Error -> throw it.exception
            }
        }
        throw IOException()
    }
}