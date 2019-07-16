package com.example.kayu

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_nutrition.view.*


private const val ARG_PRODUCT = "product"

class ProductNutriFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(product: Product): ProductNutriFragment {
            val fragment = ProductNutriFragment()
            val arguments = Bundle()
            arguments.putParcelable(ARG_PRODUCT, product)
            fragment.arguments = arguments
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_nutrition, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val product = arguments!!.getParcelable<Product>(ARG_PRODUCT)!!
        applyNutriFacts(view, product)
    }
}

private const val floatFormatter: String = "%.1f"

fun applyNutriFacts(view: View, product: Product) {
    view.fat.text = view.context.getString(
        R.string.of_fat,
        floatFormatter.format(product.nutriFacts.fat.quantityFor100),
        product.nutriFacts.fat.unit
    )
    view.fat_acids.text = view.context.getString(
        R.string.of_fat_acids,
        floatFormatter.format(product.nutriFacts.fat_acids.quantityFor100),
        product.nutriFacts.fat_acids.unit
    )
    view.salt.text = view.context.getString(
        R.string.of_salt,
        floatFormatter.format(product.nutriFacts.salt.quantityFor100),
        product.nutriFacts.salt.unit
    )
    view.sugar.text = view.context.getString(
        R.string.of_sugar,
        floatFormatter.format(product.nutriFacts.sugar.quantityFor100),
        product.nutriFacts.sugar.unit
    )

    val (fatRate: String, fatText: String) = getIndicator(view.context, "fat", product.nutriFacts.fat.quantityFor100)
    val (fatAcidsRate: String, fatAcidsText: String) = getIndicator(
        view.context,
        "fat_acids",
        product.nutriFacts.fat_acids.quantityFor100
    )
    val (sugarRate: String, sugarText: String) = getIndicator(
        view.context,
        "sugar",
        product.nutriFacts.sugar.quantityFor100
    )
    val (saltRate: String, saltText: String) = getIndicator(
        view.context,
        "salt",
        product.nutriFacts.salt.quantityFor100
    )

    view.fat_quantity.text = fatText
    view.fat_acids_quantity.text = fatAcidsText
    view.sugar_quantity.text = sugarText
    view.salt_quantity.text = saltText

    DrawableCompat.setTintList(
        view.fat_indicator.background, ColorStateList.valueOf(
            view.context.resources.getColor(getBackgroundColor(fatRate))
        )
    )
    DrawableCompat.setTintList(
        view.fat_acids_indicator.background, ColorStateList.valueOf(
            view.context.resources.getColor(getBackgroundColor(fatAcidsRate))
        )
    )
    DrawableCompat.setTintList(
        view.sugar_indicator.background, ColorStateList.valueOf(
            view.context.resources.getColor(getBackgroundColor(sugarRate))
        )
    )
    DrawableCompat.setTintList(
        view.salt_indicator.background, ColorStateList.valueOf(
            view.context.resources.getColor(getBackgroundColor(saltRate))
        )
    )

}

val intervals: Map<String, Map<String, Float>> = mapOf(
    "fat" to mapOf<String, Float>(
        "low" to 3.0f,
        "moderate" to 20.0f
    ),
    "fat_acids" to mapOf<String, Float>(
        "low" to 1.5f,
        "moderate" to 5.0f
    ),
    "salt" to mapOf<String, Float>(
        "low" to 0.3f,
        "moderate" to 1.5f
    ),
    "sugar" to mapOf<String, Float>(
        "low" to 5.0f,
        "moderate" to 12.5f
    )
)

fun getBackgroundColor(rate: String): Int {
    return when (rate) {
        "low" -> R.color.nutrient_level_low
        "moderate" -> R.color.nutrient_level_moderate
        else -> R.color.nutrient_level_high
    }
}

fun getIndicator(context: Context, nutriment: String, quantity: Float?): Pair<String, String> {
    if (quantity == null) {
        return Pair("moderate", context.getString(R.string.not_given))
    }
    return when {
        quantity.compareTo(intervals.get(nutriment)!!.get("low")!!) < 0 -> Pair(
            "low",
            context.getString(R.string.low_quantity)
        )
        quantity.compareTo(intervals.get(nutriment)!!.get("moderate")!!) < 0 -> Pair(
            "moderate",
            context.getString(R.string.medium_quantity)
        )
        else -> Pair("high", context.getString(R.string.high_quantity))
    }
}


