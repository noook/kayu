package com.example.kayu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_cell.view.*

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

    fun bindProduct(product: Product) {
        name.text = product.name
        brand.text = product.brand
        nutriscore.text = "Nutriscore: ${product.nutriscore}"
        calories.text = "${(1..600).shuffled().first()} kCal/part"
        Picasso.get().load(product.picUrl).into(picture)
    }
}