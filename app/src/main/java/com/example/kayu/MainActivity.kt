package com.example.kayu

import android.content.res.Resources
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fiche)
        supportActionBar?.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.title_gradiant))
        Toast.makeText(applicationContext,"Hello world :)",Toast.LENGTH_SHORT).show()
        Log.v("MainActivity", "Hello world")

        var product = Product(
            "Petits pois et carottes",
            "Cassegrain",
            "3083680085304",
            "E",
            "https://static.openfoodfacts.org/images/products/308/368/008/5304/front_fr.7.400.jpg",
            "400 g (280 g net égoutté)",
            "France, Japon, Suisse",
            "Petits pois 66%, eau, garniture 2,8% (salade, oignon grelot), sucre, sel, arôme naturel",
            "Aucune",
            "Aucun"
        )

        this.updateValues(product)

        /*findViewById<TextView>(R.id.my_text).setOnClickListener {
            Log.d("VIEW", "Clicked on app title !")
        }*/
    }

    fun updateValues(product: Product) {
        findViewById<TextView>(R.id.product_title).text = product.name
        findViewById<TextView>(R.id.product_brand).text = product.brand
        findViewById<TextView>(R.id.barcode).setTitleValue(applicationContext.getString(R.string.barcode), product.barcode)
        findViewById<TextView>(R.id.quantity).setTitleValue(applicationContext.getString(R.string.quantity), product.quantity)
        findViewById<TextView>(R.id.sold_in).setTitleValue(applicationContext.getString(R.string.sold_in), product.soldIn)
        findViewById<TextView>(R.id.ingredients).setTitleValue(applicationContext.getString(R.string.ingredients), product.ingredients)
        findViewById<TextView>(R.id.allergenes).setTitleValue(applicationContext.getString(R.string.allergenes), product.allergenes)
        findViewById<TextView>(R.id.label_additives).setTitleValue(applicationContext.getString(R.string.additives), product.additives)
        findViewById<ImageView>(R.id.nutri_score).setImageResource(Product.scores[product.nutriscore.toLowerCase()]!!)
        Picasso.get().load(product.picUrl).into(findViewById<ImageView>(R.id.item_picture))
    }
}

fun TextView.setTitleValue(title: String, value: String) {
    text = SpannableString("$title: $value").apply {
        setSpan(StyleSpan(Typeface.BOLD), 0, title.length + 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    }
}

class Product(
    val name: String,
    val brand: String,
    val barcode: String,
    val nutriscore: String,
    val picUrl: String,
    val quantity: String,
    val soldIn: String,
    val ingredients: String,
    val allergenes: String,
    val additives: String) {
    companion object {
        val scores = mapOf(
            "a" to R.drawable.nutri_score_a,
            "b" to R.drawable.nutri_score_b,
            "c" to R.drawable.nutri_score_c,
            "d" to R.drawable.nutri_score_d,
            "e" to R.drawable.nutri_score_e
        )
    }
}
