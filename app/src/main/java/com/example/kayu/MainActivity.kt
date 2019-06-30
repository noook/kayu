package com.example.kayu

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.empty_list.*
import kotlinx.android.synthetic.main.list.*

class MainActivity : AppCompatActivity() {

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fiche)
        supportActionBar?.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.title_gradiant))
        Toast.makeText(applicationContext, "Hello world :)", Toast.LENGTH_SHORT).show()
        Log.v("MainActivity", "Hello world")

        var product = Product(
            "Petits pois et carottes",
            "Cassegrain",
            "3083680085304",
            "E",
            "https://static.openfoodfacts.org/images/products/308/368/008/5304/front_fr.7.400.jpg",
            "400 g (280 g net égoutté)",
            listOf("France", "Japon", "Suisse"),
            listOf("Petits pois 66%", "eau", "garniture 2,8% (salade, oignon grelot)", "sucre", "sel", "arôme naturel"),
            listOf(),
            listOf()
        )

        this.updateValues(product)

        /*findViewById<TextView>(R.id.my_text).setOnClickListener {
            Log.d("VIEW", "Clicked on app title !")
        }*/
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.title_gradiant))

        val products = listOf<Product>(
            /*Product(
                "Petits pois et carottes",
                "Cassegrain",
                "3083680085304",
                "E",
                "https://static.openfoodfacts.org/images/products/308/368/008/5304/front_fr.7.400.jpg",
                "400 g (280 g net égoutté)",
                listOf("France", "Japon", "Suisse"),
                listOf("Petits pois 66%", "eau", "garniture 2,8% (salade, oignon grelot)", "sucre", "sel", "arôme naturel"),
                listOf(),
                listOf(),
                NutritionFactsItem(
                    "kCal",
                    (1..600).shuffled().first(),
                    (1..600).shuffled().first()
                ),
                Math.random() < 0.5
            ),
            Product(
                "Petits pois et carottes",
                "Cassegrain",
                "3083680085304",
                "E",
                "https://static.openfoodfacts.org/images/products/308/368/008/5304/front_fr.7.400.jpg",
                "400 g (280 g net égoutté)",
                listOf("France", "Japon", "Suisse"),
                listOf("Petits pois 66%", "eau", "garniture 2,8% (salade, oignon grelot)", "sucre", "sel", "arôme naturel"),
                listOf(),
                listOf(),
                NutritionFactsItem(
                    "kCal",
                    (1..600).shuffled().first(),
                    (1..600).shuffled().first()
                ),
                Math.random() < 0.5
            ),
            Product(
                "Petits pois et carottes",
                "Cassegrain",
                "3083680085304",
                "E",
                "https://static.openfoodfacts.org/images/products/308/368/008/5304/front_fr.7.400.jpg",
                "400 g (280 g net égoutté)",
                listOf("France", "Japon", "Suisse"),
                listOf("Petits pois 66%", "eau", "garniture 2,8% (salade, oignon grelot)", "sucre", "sel", "arôme naturel"),
                listOf(),
                listOf(),
                NutritionFactsItem(
                    "kCal",
                    (1..600).shuffled().first(),
                    (1..600).shuffled().first()
                ),
                Math.random() < 0.5
            ),
            Product(
                "Petits pois et carottes",
                "Cassegrain",
                "3083680085304",
                "E",
                "https://static.openfoodfacts.org/images/products/308/368/008/5304/front_fr.7.400.jpg",
                "400 g (280 g net égoutté)",
                listOf("France", "Japon", "Suisse"),
                listOf("Petits pois 66%", "eau", "garniture 2,8% (salade, oignon grelot)", "sucre", "sel", "arôme naturel"),
                listOf(),
                listOf(),
                NutritionFactsItem(
                    "kCal",
                    (1..600).shuffled().first(),
                    (1..600).shuffled().first()
                ),
                Math.random() < 0.5
            )*/
        )

        if (products.isNotEmpty()) {
            setContentView(R.layout.list)
            list.layoutManager = LinearLayoutManager(this)
            list.adapter = ProductAdapter(products)
        } else {
            setContentView(R.layout.empty_list)
            scan_button.setOnClickListener {
                intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("name", "toto")
                startActivity(intent)
            }
        }
    }

    fun updateValues(product: Product) {
        findViewById<TextView>(R.id.product_title).text = product.name
        findViewById<TextView>(R.id.product_brand).text = product.brand
        findViewById<TextView>(R.id.barcode).setTitleValue(
            applicationContext.getString(R.string.barcode),
            product.barcode
        )
        findViewById<TextView>(R.id.quantity).setTitleValue(
            applicationContext.getString(R.string.quantity),
            product.quantity
        )
        findViewById<TextView>(R.id.sold_in).setTitleValue(
            applicationContext.getString(R.string.sold_in),
            if (product.soldIn.isEmpty()) "Nulle part" else product.soldIn.joinToString(separator = ", ")
        )
        findViewById<TextView>(R.id.ingredients).setTitleValue(
            applicationContext.getString(R.string.ingredients),
            product.ingredients.joinToString(separator = ", ")
        )
        findViewById<TextView>(R.id.allergenes).setTitleValue(
            applicationContext.getString(R.string.allergenes),
            if (product.allergenes.isEmpty()) "Aucune" else product.allergenes.joinToString(separator = ", ")
        )
        findViewById<TextView>(R.id.label_additives).setTitleValue(
            applicationContext.getString(R.string.additives),
            if (product.additives.isEmpty()) "Aucun" else product.additives.joinToString(separator = ", ")
        )
        findViewById<ImageView>(R.id.nutri_score).setImageResource(
            resources.getIdentifier("nutri_score_${product.nutriscore.toLowerCase()}", "drawable", packageName)
        )
        Picasso.get().load(product.picUrl).into(findViewById<ImageView>(R.id.item_picture))
    }
}

fun TextView.setTitleValue(title: String, value: String) {
    text = SpannableString("$title: $value").apply {
        setSpan(StyleSpan(Typeface.BOLD), 0, title.length + 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    }
}
