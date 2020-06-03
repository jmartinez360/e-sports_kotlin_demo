package com.example.lolresults.datasource

import com.example.lolresults.api.ApiResult
import java.io.IOException

suspend fun <T : Any> safeApiCall(call: suspend () -> ApiResult<T>): ApiResult<T> = try {
    call.invoke()
} catch (e: Exception) {
    ApiResult.Error(IOException("DEVELOPER ERROR", e))
}