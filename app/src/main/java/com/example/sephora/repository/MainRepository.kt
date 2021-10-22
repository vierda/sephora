package com.example.sephora.repository

import android.text.Html
import com.example.sephora.model.Product
import com.example.sephora.model.database.SephoraDatabase
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MainRepository @Inject constructor(
    private val database: SephoraDatabase,
    private val networkDataSource: NetworkDataSource,
    private val gson: Gson) : CoroutineScope {

    private val parentJob = SupervisorJob()
    override val coroutineContext: CoroutineContext get() = Dispatchers.IO + parentJob

    suspend fun fetchProducts(): Flow<ArrayList<Product>> {
        val products = ArrayList<Product>()
        return flow {
            val result = networkDataSource.fetchProducts()
            if (result.isSuccess) {
                val json = result.getOrNull()
                Timber.e("received data $json")
                val jsonArray = json?.get("data")?.asJsonArray
                jsonArray?.forEach {
                    val product = toObjectMapper(it.asJsonObject)
                    products.add(product)
                }
                database.productDao().insertAll(products)
                emit(products)
            } else {
                emit(products)
            }

        }.flowOn(Dispatchers.IO)
    }

    private fun toObjectMapper(json: JsonObject): Product {
        val product = Product()
        product.productId = json.get("id").asInt

        val attributes = json.get("attributes").asJsonObject
        product.productName = attributes.get("brand-name").asString
        product.originalPrice = attributes.get("display-original-price").asString
        product.price = attributes.get("display-price").asString
        product.shortDescription = attributes.get("name").asString
        product.discount = attributes.get("sale-text").asString
        product.rating = attributes.get("rating").asFloat
        product.totalReview = attributes.get("reviews-count").asInt

        val ingredients = attributes.get("ingredients").asString
        product.formulation = Html.fromHtml(ingredients, Html.FROM_HTML_MODE_LEGACY).toString()

        val howTo = attributes.get("how-to-text").asString
        product.detailDescription = Html.fromHtml(howTo, Html.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH).toString()

        val description = attributes.get("description").asString
        product.longDetailDescription = Html.fromHtml(description, Html.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH).toString()

        val images = attributes.getAsJsonArray("zoom-image-urls").toString()
        product.productImages = gson.fromJson(images, Array<String>::class.java).asList()

        val icons = attributes.getAsJsonArray("icon-image-urls").toString()
        product.iconImage = gson.fromJson(icons, Array<String>::class.java).asList()

        product.function = "Hydrate, Long-wearing, Smoothen"
        product.skinType = "Combination, Dry, Normal, Oily"
        product.finish = "Natural"

        return product
    }

    fun getProductById (productId:Int) : Product {
       return database.productDao().getProductById(productId)
   }
}