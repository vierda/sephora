package com.example.sephora.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.sephora.model.database.SephoraConverter

@Entity(tableName = "product")
class Product {

    @PrimaryKey
    @ColumnInfo(name = "product_id")
    var productId: Int = 0

    @ColumnInfo(name = "product_name")
    var productName: String = ""

    @ColumnInfo(name = "original_price")
    var originalPrice: String = ""

    @ColumnInfo(name = "price")
    var price: String = ""

    @ColumnInfo(name = "short_description")
    var shortDescription: String = ""

    @ColumnInfo(name = "discount")
    var discount: String = ""

    @ColumnInfo(name = "rating")
    var rating: Float = 0f

    @ColumnInfo(name = "total_review")
    var totalReview: Int = 0

    @ColumnInfo(name = "function")
    var function: String = ""

    @ColumnInfo(name = "skin_type")
    var skinType: String = ""

    @ColumnInfo(name = "finish")
    var finish: String = ""

    @ColumnInfo(name = "formulation")
    var formulation: String = ""

    @ColumnInfo(name = "detail_description")
    var detailDescription: String = ""

    @ColumnInfo(name = "long_detail_description")
    var longDetailDescription: String = ""

    @TypeConverters(SephoraConverter::class)
    @ColumnInfo(name = "product_images")
    var productImages: List<String> = ArrayList()

    @TypeConverters(SephoraConverter::class)
    @ColumnInfo(name = "icon_image")
    var iconImage:  List<String> = ArrayList()
}