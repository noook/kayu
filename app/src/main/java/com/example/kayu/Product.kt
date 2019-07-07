package com.example.kayu

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val name: String,
    val brand: String,
    val barcode: String,
    val nutriscore: String,
    val picUrl: String,
    val quantity: String,
    val soldIn: List<String>,
    val ingredients: List<String>,
    val allergenes: List<String>,
    val additives: List<String>,
    val nutriFacts: NutritionFacts,
    val bookmarked: Boolean
): Parcelable

@Parcelize
data class NutritionFactsItem(
    val unit: String,
    val quantity_per_portion: Float,
    val quantityFor100: Float
): Parcelable

@Parcelize
data class NutritionFacts(
    val calories: NutritionFactsItem,
    val fat: NutritionFactsItem,
    val fat_acids: NutritionFactsItem,
    val glucids: NutritionFactsItem,
    val sugar: NutritionFactsItem,
    val fibers: NutritionFactsItem,
    var proteins: NutritionFactsItem,
    val salt: NutritionFactsItem,
    val sodium: NutritionFactsItem
): Parcelable {
    val map = mapOf(
        "calories" to this.calories,
        "fat" to this.fat,
        "fat_acids" to this.fat_acids,
        "glucids" to this.glucids,
        "sugar" to this.sugar,
        "fibers" to this.fibers,
        "proteins" to this.proteins,
        "salt" to this.salt,
        "sodium" to this.sodium
    )

    fun getProperty(property: String): NutritionFactsItem {
        return this.map[property]!!
    }
}
