package com.example.sephora

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.example.sephora.model.Product
import com.example.sephora.model.database.ProductDao
import com.example.sephora.model.database.SephoraDatabase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class ProductDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: SephoraDatabase
    private lateinit var dao: ProductDao

    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.productDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertListProduct() = runBlockingTest {

        val product = Product()
        product.productId = 1
        product.productName = "Baby Powder"
        product.originalPrice = "$10"
        product.price = "$5"
        product.shortDescription = "Very smooth"
        product.discount = "30%"
        product.rating = 4.5f
        product.totalReview = 1331
        product.formulation = "Powder"
        product.detailDescription = "Smooth and Silky"
        product.longDetailDescription = "Smooth and Silky"
        product.productImages =
            listOf("https://image-optimizer-reg.production.sephora-asia.net/images/product_images/1142_ZOEVA_WEB_57c7554d0dd6256f29fdd1ec6890bd2f07fb44c8_1523776436.png")
        product.iconImage =
            listOf("https://image-optimizer-reg.production.sephora-asia.net/images/product_images/1142_ZOEVA_WEB_57c7554d0dd6256f29fdd1ec6890bd2f07fb44c8_1523776436.png")
        product.function = "Hydrate, Long-wearing, Smoothen"
        product.skinType = "Combination, Dry, Normal, Oily"
        product.finish = "Natural"

        val list = ArrayList<Product>()
        list.add(product)
        dao.insertAll(list)

        val productInDb = dao.getProductById(1)

        assert(productInDb.productId == 1)
        assert(productInDb.productName == "Baby Powder")
        assert(productInDb.productName != "Baby Shampoo")
    }
}