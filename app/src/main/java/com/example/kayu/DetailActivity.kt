package com.example.kayu

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val actionBar = findViewById(R.id.toolbar) as Toolbar?
        setSupportActionBar(actionBar)
        supportActionBar?.setTitle(R.string.product_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.title_gradiant))

        var barcode = intent.getStringExtra("barcode")
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
