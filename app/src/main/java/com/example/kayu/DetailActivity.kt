package com.example.kayu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.*
import java.lang.Exception


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setSupportActionBar(toolbar)
        setContentView(R.layout.activity_detail)

        var barcode = intent.getStringExtra("barcode")
        println("HERE $barcode") // null ???
        barcode = "3083680085304" // Using this one for the moment
        val api: API = API.create()
        val tabs = findViewById<TabLayout>(R.id.tabs)
        val viewPager = findViewById<ViewPager>(R.id.viewpager)

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val product = withContext(Dispatchers.IO) {
                    api.getProduct(barcode).await().response?.toProduct()!!
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
