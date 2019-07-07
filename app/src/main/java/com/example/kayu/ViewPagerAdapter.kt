package com.example.kayu

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ProductDetailsAdapter(fm: FragmentManager, val product: Product) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ProductDetailFragment.newInstance(product)
            1 -> ProductNutriFragment.newInstance(product)
            2 -> ProductDetailFragment.newInstance(product)
            else -> throw Exception("Unknown position")
        }
    }

    override fun getCount(): Int = 3

    // TODO Utiliser des ressources
    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Fiche"
            1 -> "Nutrition"
            2 -> "Infos nutritionnelles"
            else -> throw Exception("Unknown position")
        }
    }
}