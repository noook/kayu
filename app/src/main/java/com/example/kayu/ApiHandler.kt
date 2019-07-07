package com.example.kayu

import com.google.gson.annotations.SerializedName
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface API {

    @GET("getProduct")
    fun getProduct(@Query("barcode") barcode: String): Deferred<ServerResponse>

    companion object Factory {
        fun create(): API {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .baseUrl("https://api.formation-android.fr")
                .build()
                .create(API::class.java)
        }
    }
}

data class ServerResponse(
    @SerializedName("error")
    val error: String?,
    @SerializedName("response")
    val response: Response?
) {
    data class Response(
        @SerializedName("additives")
        val additives: Map<String, String>?,
        @SerializedName("allergens")
        val allergens: List<String?>?,
        @SerializedName("altName")
        val altName: String?,
        @SerializedName("barcode")
        val barcode: String,
        @SerializedName("brands")
        val brands: List<String?>?,
        @SerializedName("containsPalmOil")
        val containsPalmOil: Boolean?,
        @SerializedName("ingredients")
        val ingredients: List<String?>?,
        @SerializedName("manufacturingCountries")
        val manufacturingCountries: List<String?>?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("novaScore")
        val novaScore: String?,
        @SerializedName("nutriScore")
        val nutriScore: String?,
        @SerializedName("nutritionFacts")
        val nutritionFacts: NutritionFacts?,
        @SerializedName("picture")
        val picture: String?,
        @SerializedName("quantity")
        val quantity: String?,
        @SerializedName("traces")
        val traces: List<String?>?
    ) {
        fun toProduct(): Product {
            val name: String = this.name ?: ""
            val brand: String = this.brands?.joinToString(separator = " ") ?: ""
            val barcode: String = this.barcode
            val nutriscore: String = this.nutriScore ?: "C"
            val picUrl: String =
                this.picture ?: "https://cdn.dribbble.com/users/1012566/screenshots/4187820/topic-2.jpg"
            val quantity: String = this.quantity ?: ""
            val soldIn: List<String> = this.manufacturingCountries?.filterNotNull() ?: listOf()
            val ingredients: List<String> = this.ingredients?.filterNotNull() ?: listOf()
            val allergenes: List<String> = this.allergens?.filterNotNull() ?: listOf()
            val additives: List<String> = this.additives?.keys?.toList() ?: listOf()
            val nutriFacts: com.example.kayu.NutritionFacts = this.nutritionFacts!!.toNutritionFacts()
            val bookmarked = false

            return Product(
                name,
                brand,
                barcode,
                nutriscore,
                picUrl,
                quantity,
                soldIn,
                ingredients,
                allergenes,
                additives,
                nutriFacts,
                bookmarked
            )
        }
    }

    data class NutritionFacts(
        @SerializedName("calories")
        val calories: NutritionFact?,
        @SerializedName("carbohydrate")
        val carbohydrate: NutritionFact?,
        @SerializedName("energy")
        val energy: NutritionFact?,
        @SerializedName("fat")
        val fat: NutritionFact?,
        @SerializedName("fiber")
        val fiber: NutritionFact?,
        @SerializedName("proteins")
        val proteins: NutritionFact?,
        @SerializedName("salt")
        val salt: NutritionFact?,
        @SerializedName("saturatedFat")
        val saturatedFat: NutritionFact?,
        @SerializedName("servingSize")
        val servingSize: String?,
        @SerializedName("sodium")
        val sodium: NutritionFact?,
        @SerializedName("sugar")
        val sugar: NutritionFact?
    ) {
        data class NutritionFact(
            @SerializedName("per100g")
            val per100g: String?,
            @SerializedName("perServing")
            val perServing: String?,
            @SerializedName("unit")
            val unit: String?
        )

        fun toNutritionFacts(): com.example.kayu.NutritionFacts {
            val calories = NutritionFactsItem(
                this.calories?.unit ?: "",
                this.calories?.perServing?.toFloatOrNull() ?: 0f,
                this.calories?.per100g?.toFloatOrNull() ?: 0f
            )

            val fat: NutritionFactsItem = NutritionFactsItem(
                this.fat?.unit ?: "",
                this.fat?.perServing?.toFloatOrNull() ?: 0f,
                this.fat?.per100g?.toFloatOrNull() ?: 0f
            )
            val fat_acids: NutritionFactsItem = NutritionFactsItem(
                this.saturatedFat?.unit ?: "",
                this.saturatedFat?.perServing?.toFloatOrNull() ?: 0f,
                this.saturatedFat?.per100g?.toFloatOrNull() ?: 0f
            )
            val glucids: NutritionFactsItem = NutritionFactsItem(
                this.energy?.unit ?: "",
                this.energy?.perServing?.toFloatOrNull() ?: 0f,
                this.energy?.per100g?.toFloatOrNull() ?: 0f
            )
            val sugar: NutritionFactsItem = NutritionFactsItem(
                this.sugar?.unit ?: "",
                this.sugar?.perServing?.toFloatOrNull() ?: 0f,
                this.sugar?.per100g?.toFloatOrNull() ?: 0f
            )
            val fibers: NutritionFactsItem = NutritionFactsItem(
                this.fiber?.unit ?: "",
                this.fiber?.perServing?.toFloatOrNull() ?: 0f,
                this.fiber?.per100g?.toFloatOrNull() ?: 0f
            )
            var proteins: NutritionFactsItem = NutritionFactsItem(
                this.proteins?.unit ?: "",
                this.proteins?.perServing?.toFloatOrNull() ?: 0f,
                this.proteins?.per100g?.toFloatOrNull() ?: 0f
            )
            val salt: NutritionFactsItem = NutritionFactsItem(
                this.salt?.unit ?: "",
                this.salt?.perServing?.toFloatOrNull() ?: 0f,
                this.salt?.per100g?.toFloatOrNull() ?: 0f
            )
            val sodium: NutritionFactsItem = NutritionFactsItem(
                this.sodium?.unit ?: "",
                this.sodium?.perServing?.toFloatOrNull() ?: 0f,
                this.sodium?.per100g?.toFloatOrNull() ?: 0f
            )

            return com.example.kayu.NutritionFacts(
                calories,
                fat,
                fat_acids,
                glucids,
                sugar,
                fibers,
                proteins,
                salt,
                sodium
            )
        }
    }
}