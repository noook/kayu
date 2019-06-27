package com.example.kayu

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
    val nutriFacts: NutritionFactsItem,
    val bookmarked: Boolean
) {}

data class NutritionFactsItem(
    val unit: String,
    val quantity_per_portion: Int,
    val quantityFor100: Int
) {}