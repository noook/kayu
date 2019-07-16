package com.example.kayu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_nutri_table.view.*


private const val ARG_PRODUCT = "product"
private const val floatFormatter: String = "%.1f"

class ProductNutriTableFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(product: Product): ProductNutriTableFragment {
            val fragment = ProductNutriTableFragment()
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
        return inflater.inflate(R.layout.fragment_nutri_table, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val product = arguments!!.getParcelable<Product>(ARG_PRODUCT)!!
        println(product.nutriFacts)

        view.energy_for_100.text = if (product.nutriFacts.calories.quantityFor100 != null) view.context.getString(
            R.string.value_unit,
            floatFormatter.format(product.nutriFacts.calories.quantityFor100),
            product.nutriFacts.calories.unit
        ) else "?"
        view.fat_for_100.text = if (product.nutriFacts.fat.quantityFor100 != null) view.context.getString(
            R.string.value_unit,
            floatFormatter.format(product.nutriFacts.fat.quantityFor100),
            product.nutriFacts.fat.unit
        ) else "?"
        view.saturated_fat_for_100.text = if (product.nutriFacts.fat_acids.quantityFor100 != null) view.context.getString(
            R.string.value_unit,
            floatFormatter.format(product.nutriFacts.fat_acids.quantityFor100),
            product.nutriFacts.fat_acids.unit
        ) else "?"
        view.glucids_for_100.text = if (product.nutriFacts.glucids.quantityFor100 != null) view.context.getString(
            R.string.value_unit,
            floatFormatter.format(product.nutriFacts.glucids.quantityFor100),
            product.nutriFacts.glucids.unit
        ) else "?"
        view.sugar_for_100.text = if (product.nutriFacts.sugar.quantityFor100 != null) view.context.getString(
            R.string.value_unit,
            floatFormatter.format(product.nutriFacts.sugar.quantityFor100),
            product.nutriFacts.sugar.unit
        ) else "?"
        view.fibers_for_100.text = if (product.nutriFacts.fibers.quantityFor100 != null) view.context.getString(
            R.string.value_unit,
            floatFormatter.format(product.nutriFacts.fibers.quantityFor100),
            product.nutriFacts.fibers.unit
        ) else "?"
        view.proteins_for_100.text = if (product.nutriFacts.proteins.quantityFor100 != null) view.context.getString(
            R.string.value_unit,
            floatFormatter.format(product.nutriFacts.proteins.quantityFor100),
            product.nutriFacts.proteins.unit
        ) else "?"
        view.salt_for_100.text = if (product.nutriFacts.salt.quantityFor100 != null) view.context.getString(
            R.string.value_unit,
            floatFormatter.format(product.nutriFacts.salt.quantityFor100),
            product.nutriFacts.salt.unit
        ) else "?"
        view.sodium_for_100.text = if (product.nutriFacts.sodium.quantityFor100 != null) view.context.getString(
            R.string.value_unit,
            floatFormatter.format(product.nutriFacts.sodium.quantityFor100),
            product.nutriFacts.sodium.unit
        ) else "?"

        view.energy_per_serving.text = if (product.nutriFacts.calories.quantity_per_portion != null) view.context.getString(
            R.string.value_unit,
            floatFormatter.format(product.nutriFacts.calories.quantity_per_portion),
            product.nutriFacts.calories.unit
        ) else "?"
        view.fat_per_serving.text = if (product.nutriFacts.fat.quantity_per_portion != null) view.context.getString(
            R.string.value_unit,
            floatFormatter.format(product.nutriFacts.fat.quantity_per_portion),
            product.nutriFacts.fat.unit
        ) else "?"
        view.saturated_fat_per_serving.text = if (product.nutriFacts.fat_acids.quantity_per_portion != null) view.context.getString(
            R.string.value_unit,
            floatFormatter.format(product.nutriFacts.fat_acids.quantity_per_portion),
            product.nutriFacts.fat_acids.unit
        ) else "?"
        view.glucids_per_serving.text = if (product.nutriFacts.glucids.quantity_per_portion != null) view.context.getString(
            R.string.value_unit,
            floatFormatter.format(product.nutriFacts.glucids.quantity_per_portion),
            product.nutriFacts.glucids.unit
        ) else "?"
        view.sugar_per_serving.text = if (product.nutriFacts.sugar.quantity_per_portion != null) view.context.getString(
            R.string.value_unit,
            floatFormatter.format(product.nutriFacts.sugar.quantity_per_portion),
            product.nutriFacts.sugar.unit
        ) else "?"
        view.fibers_per_serving.text = if (product.nutriFacts.fibers.quantity_per_portion != null) view.context.getString(
            R.string.value_unit,
            floatFormatter.format(product.nutriFacts.fibers.quantity_per_portion),
            product.nutriFacts.fibers.unit
        ) else "?"
        view.proteins_per_serving.text = if (product.nutriFacts.proteins.quantity_per_portion != null) view.context.getString(
            R.string.value_unit,
            floatFormatter.format(product.nutriFacts.proteins.quantity_per_portion),
            product.nutriFacts.proteins.unit
        ) else "?"
        view.salt_per_serving.text = if (product.nutriFacts.salt.quantity_per_portion != null) view.context.getString(
            R.string.value_unit,
            floatFormatter.format(product.nutriFacts.salt.quantity_per_portion),
            product.nutriFacts.salt.unit
        ) else "?"
        view.sodium_per_serving.text = if (product.nutriFacts.sodium.quantity_per_portion != null) view.context.getString(
            R.string.value_unit,
            floatFormatter.format(product.nutriFacts.sodium.quantity_per_portion),
            product.nutriFacts.sodium.unit
        ) else "?"
    }
}
