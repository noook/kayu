package com.example.kayu

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fiche)

        /*findViewById<TextView>(R.id.my_text).setOnClickListener {
            Log.d("VIEW", "Clicked on app title !")
        }*/
    }
}
