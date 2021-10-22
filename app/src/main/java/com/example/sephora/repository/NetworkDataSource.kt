package com.example.sephora.repository

import com.google.gson.JsonObject
import retrofit2.Response
import javax.inject.Inject

class NetworkDataSource @Inject constructor(private val webManager: WebManager)  {


    private suspend fun <T> getResponse(request: suspend () -> Response<T>,
        defaultErrorMessage: String): Result<T> {
        return try {
           val result = request.invoke()
            if (result.isSuccessful)
                Result.success(result.body()!!)
            else
                Result.failure(Throwable(defaultErrorMessage))

        } catch (e: Throwable) {
            Result.failure(e)
        }
    }


    suspend fun fetchProducts(): Result<JsonObject> {
        return getResponse(
            request = { webManager.getProduct() },
            defaultErrorMessage = "Error fetching product list"
        )
    }

}