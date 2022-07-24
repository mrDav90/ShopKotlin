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
        var beautyBt = findViewById<Button>(R.id.beautyBt);


        shoesBt.setOnClickListener {
            intent = Intent(this , Add::class.java);
            intent.putExtra("category" , "chaussure");
            startActivity(intent)
        }

        shirtsBt.setOnClickListener {
            intent = Intent(this , Add::class.java);
            intent.putExtra("category" , "t-shirt");
            startActivity(intent)
        }

        jeansBt.setOnClickListener {
            intent = Intent(this , Add::class.java);
            intent.putExtra("category" , "jeans");
            startActivity(intent)
        }

        beautyBt.setOnClickListener {
            intent = Intent(this , Add::class.java);
            intent.putExtra("category" , "beauté");
            startActivity(intent)
        }
    }
}