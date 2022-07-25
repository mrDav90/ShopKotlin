package com.example.shopappkotlin

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class ProductDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)




        val nameText = findViewById<TextView>(R.id.nameText)
        val priceText = findViewById<TextView>(R.id.priceText)
        val descriptionText = findViewById<TextView>(R.id.descriptionText)
        val categoryText = findViewById<TextView>(R.id.categoryText)
        val image = findViewById<ImageView>(R.id.image)

        val bundle: Bundle? = intent.extras
        val theProductName = bundle?.get("productName")
        val theProductPrice = bundle?.get("productPrice")
        val theProductDescription = bundle?.get("productDescription")
        val theProductCategory = bundle?.get("productCategory")
        val theProductImage = bundle?.get("productImage")
        val bitmap = getBitmap(theProductImage as ByteArray)


        nameText.setText(theProductName.toString());
        priceText.setText(theProductPrice.toString());
        descriptionText.setText(theProductDescription.toString())
        categoryText.setText(theProductCategory.toString())
        image.setImageBitmap(bitmap)

        val returnButton = findViewById<Button>(R.id.returnButton)
        returnButton.setOnClickListener {
            intent = Intent(this , Shop::class.java)
            startActivity(intent);

        }

    }
    fun getBitmap(byteArray: ByteArray ) : Bitmap {
        val bitmap = BitmapFactory.decodeByteArray(byteArray , 0 , byteArray.size)
        return bitmap
    }

}