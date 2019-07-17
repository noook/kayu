package com.example.kayu

import android.app.AlertDialog
import android.app.ProgressDialog
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
import org.jetbrains.anko.*


class DetailActivity : AppCompatActivity() {

    var api: API = API.create()

    lateinit var dialog: ProgressDialog
    lateinit var viewPager: ViewPager
    lateinit var tabs: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val actionBar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(actionBar)
        supportActionBar?.setTitle(R.string.product_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.title_gradiant))

        var barcode = intent.getStringExtra("barcode")
        this.tabs = findViewById<TabLayout>(R.id.tabs)
        this.viewPager = findViewById<ViewPager>(R.id.viewpager)
        this.dialog = indeterminateProgressDialog(message = applicationContext.getString(R.string.loading))

        makeRequest(barcode)
    }

    fun makeRequest(barcode: String) {

        dialog.show()
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val product = withContext(Dispatchers.IO) {
                    api.getProduct(barcode).await().response?.toProduct()!!
                }
                dialog.dismiss()
                viewPager.adapter = ProductDetailsAdapter(supportFragmentManager, product, applicationContext)
                tabs.setupWithViewPager(viewPager)
            } catch (e: Exception) {
                dialog.dismiss()
                requestError(barcode).show()
                print(e.printStackTrace())
            }
        }
    }

    fun requestError(barcode: String): AlertBuilder<AlertDialog> {
        return alert(applicationContext.getString(R.string.error_occurred)) {
            yesButton { makeRequest(barcode) }
            noButton { finish() }
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
