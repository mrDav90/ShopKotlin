package com.example.shopappkotlin

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.button.MaterialButton

class Connexion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connexion)

        val identifiant = findViewById<EditText>(R.id.username)
        val mdp = findViewById<EditText>(R.id.password)
        val btn = findViewById<MaterialButton>(R.id.loginBtn)
        var ad = AlertDialog.Builder(this)


        btn.setOnClickListener{
            if (identifiant.text.toString().equals("Admin") && mdp.text.toString().equals("Password") ){
                intent = Intent(this , Shop::class.java);
                startActivity(intent)
                Toast.makeText(applicationContext, "Bienvenue cher admin !!", Toast.LENGTH_LONG).show()
                identifiant.setText("")
                mdp.setText("")
            }else{
                ad.setTitle("Erreur")
                ad.setMessage(" Identifiant ou mot de passe incorrect !!!")
                ad.setPositiveButton("OK", DialogInterface.OnClickListener{ dialogInterface, i ->
                    identifiant.requestFocus()
                    mdp.setText("")
                })
                ad.show()
            }
        }

        /*val startBtn = findViewById<Button>(R.id.connexionBtn);
        startBtn.setOnClickListener {
            intent = Intent(this , Shop::class.java);
            startActivity(intent)
        }*/
    }
}