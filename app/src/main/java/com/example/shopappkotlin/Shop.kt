package com.example.shopappkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Shop : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        var shoesBt = findViewById<Button>(R.id.shoesBt);
        var shirtsBt = findViewById<Button>(R.id.shirtsBt);
        var jeansBt = findViewById<Button>(R.id.jeansBt);
        var watchBt = findViewById<Button>(R.id.watchBtn);


        shoesBt.setOnClickListener {
            intent = Intent(this , ProductsList::class.java);
            intent.putExtra("category" , "chaussure");
            startActivity(intent)
        }

        shirtsBt.setOnClickListener {
            intent = Intent(this , ProductsList::class.java);
            intent.putExtra("category" , "t-shirt");
            startActivity(intent)
        }

        jeansBt.setOnClickListener {
            intent = Intent(this , ProductsList::class.java);
            intent.putExtra("category" , "jeans");
            startActivity(intent)
        }

        watchBt.setOnClickListener {
            intent = Intent(this , ProductsList::class.java);
            intent.putExtra("category" , "montres");
            startActivity(intent)
        }
    }
}