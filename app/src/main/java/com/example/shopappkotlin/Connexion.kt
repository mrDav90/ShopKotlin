package com.example.shopappkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Connexion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connexion)

        val startBtn = findViewById<Button>(R.id.connexionBtn);
        startBtn.setOnClickListener {
            intent = Intent(this , Shop::class.java);
            startActivity(intent)
        }
    }
}