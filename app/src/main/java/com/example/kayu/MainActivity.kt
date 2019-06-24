package com.example.kayu

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fiche)
        supportActionBar?.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.title_gradiant))
        Toast.makeText(applicationContext,"Hello world :)",Toast.LENGTH_SHORT).show()
        Log.v("MainActivity", "Hello world")

        /*findViewById<TextView>(R.id.my_text).setOnClickListener {
            Log.d("VIEW", "Clicked on app title !")
        }*/
    }
}
