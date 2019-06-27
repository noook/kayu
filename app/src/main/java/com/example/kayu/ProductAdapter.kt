package com.example.kayu

import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_cell.view.*
import java.security.AccessController.getContext

class ProductAdapter(val products: List<Product>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ProductItemCell(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_cell, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ProductItemCell).bindProduct(products[position])
    }
}

class ProductItemCell(v: View) : RecyclerView.ViewHolder(v) {
    private val name: TextView = v.product_name
    private val brand: TextView = v.product_brand
    private val calories: TextView = v.label_calories
    private val nutriscore: TextView = v.label_nutriscore
    private val picture: ImageView = v.product_image
    private val bookmark: ImageView = v.bookmark

    fun bindProduct(product: Product) {
        name.text = product.name
        brand.text = product.brand
        nutriscore.text = nutriscore.context.getString(R.string.nutriscore_value, product.nutriscore)
        calories.text = calories.context.getString(R.string.calories_per_portion, product.nutriFacts.quantity_per_portion)
        Picasso.get().load(product.picUrl).into(picture)
        if (product.bookmarked) {
            bookmark.setColorFilter(ContextCompat.getColor(bookmark.context, R.color.bookmarked), android.graphics.PorterDuff.Mode.SRC_IN);
        }
    }
}