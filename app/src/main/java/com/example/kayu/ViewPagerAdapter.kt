package com.example.kayu

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ProductDetailsAdapter(fm: FragmentManager, val product: Product, private val context: Context) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ProductDetailFragment.newInstance(product)
            1 -> ProductNutriFragment.newInstance(product)
            2 -> ProductNutriTableFragment.newInstance(product)
            else -> throw Exception("Unknown position")
        }
    }

    override fun getCount(): Int = 3

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> context.getString(R.string.card)
            1 -> context.getString(R.string.nutrition)
            2 -> context.getString(R.string.nutri_infos)
            else -> throw Exception("Unknown position")
        }
    }
}