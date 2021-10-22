package com.example.sephora.repository

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface WebManager {

    companion object {

        private const val protocol = "https"
        private const val serverName = "api.sephora.sg/api/v2.5/"
        private const val productPath = "products?page[number]=1page[size]=30&include=featured_variant,featured_ad&filter[landing_page]=sale&include=brand,option_types.option_values,featured_variant,featured_ad&sort=sales"

        private fun getHost(): String {
            val builder = StringBuilder(1024)

            builder.append(protocol)
                .append("://")
                .append(serverName)

            return builder.toString()
        }


        private fun getHttpClient(): OkHttpClient {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.HEADERS

            val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
            httpClient.addInterceptor(interceptor)
            return httpClient.build()
        }

        fun create(): WebManager {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(getHost())
                .client(getHttpClient())
                .build()

            return retrofit.create(WebManager::class.java)
        }
    }


    @Headers("X-OS-Name: android","X-App-Platform: mobileapp_android","X-Platform: mobile_app","X-Site-Country: SG","Accept-Language: en-SG")
    @GET("$productPath")
    suspend fun getProduct(): Response<JsonObject>

}