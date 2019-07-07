package com.example.kayu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_detail.*
import java.net.URL
import com.google.gson.Gson
import kotlinx.coroutines.*
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setSupportActionBar(toolbar)
        setContentView(R.layout.activity_detail)
        val barcode = intent.extras.getString("barcode")
        val api: API = API.create()
        val tabs = findViewById<TabLayout>(R.id.tabs)
        val viewPager = findViewById<ViewPager>(R.id.viewpager)

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val product = withContext(Dispatchers.IO) {
                    api.getProduct("3083680085304").await().response?.toProduct()!!
                }
                viewPager.adapter = ProductDetailsAdapter(supportFragmentManager, product)
                tabs.setupWithViewPager(viewPager)
            } catch (e: Exception) {
                println("An error occurred while doing the request.")
                print(e.printStackTrace())
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}


fun getProductFromBarcode(barcode: String): Product {
    val url = URL("https://api.formation-android.fr/getProduct?barcode=$barcode")
    return Gson().fromJson(url.readText(), Product::class.java)
}

