package com.example.sephora.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sephora.model.Product
import java.util.ArrayList

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(products: ArrayList<Product>)

    @Query("SELECT * from product WHERE product_id LIKE :productId")
    fun getProductById (productId:Int) : Product
}