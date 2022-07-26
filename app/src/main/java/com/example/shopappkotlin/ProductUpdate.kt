package com.example.shopappkotlin

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.example.shopappkotlin.data.Product
import com.example.shopappkotlin.db.Database
import java.io.ByteArrayOutputStream

class ProductUpdate : AppCompatActivity() {
    var bitmap : Bitmap? = null
    lateinit var db : Database
    lateinit var upProductImage : ImageButton
    lateinit var upProductName : EditText
    lateinit var upProductPrice : EditText
    lateinit var upProductDescription : EditText
    lateinit var updateProductButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_update)
        db = Database(this)
        upProductName = findViewById(R.id.upProductName)
        upProductPrice = findViewById(R.id.upProductPrice)
        upProductDescription = findViewById(R.id.upProductDescription)
        upProductImage = findViewById(R.id.upProductImage)
        updateProductButton = findViewById(R.id.updateProductButton)
        val bundle: Bundle? = intent.extras
        val productId = bundle?.get("productId1")
        val theProductName = bundle?.get("productName1")
        val theProductDescription = bundle?.get("productDescription1")
        val theProductPrice = bundle?.get("productPrice1")
        val theProductCategory = bundle?.get("productCategory1")

        val galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { data ->
            val inputStream = contentResolver.openInputStream(data)
            bitmap = BitmapFactory.decodeStream(inputStream)
            upProductImage.setImageBitmap(bitmap);
        }

        upProductImage.setOnClickListener{
            // just for open the gallery
            galleryLauncher.launch("image/*");
        }

        upProductName.setText("${theProductName}");
        upProductPrice.setText("${theProductPrice}");
        upProductDescription.setText("${theProductDescription}");



        updateProductButton.setOnClickListener {
            Toast.makeText(this , "tesssssssst" , Toast.LENGTH_SHORT).show()
            // Reprendre les nouvelles données entrées
            /*val upProductName2 = findViewById<EditText>(R.id.upProductName)
            val upProductPrice2 = findViewById<EditText>(R.id.upProductPrice)
            val upProductDescription2 = findViewById<EditText>(R.id.upProductDescription)
            val upProductImage2 = findViewById<ImageButton>(R.id.upProductImage)

           val proName = upProductName2.text.toString();
            val proPrice = upProductPrice2.text.toString();
            val proDescription = upProductDescription2.text.toString();
            val proCategory = theProductCategory.toString();
            val proImageBlob: ByteArray = getBytes(bitmap!!);




            //val product = Product(proName, proPrice, proDescription, proCategory, proImageBlob)
            val result = db.updateProduct(productId as Int,  proName , proPrice , proDescription , proCategory , proImageBlob)
            if (result) {
                var ad = AlertDialog.Builder(this);
                ad.setTitle("Succès");
                ad.setMessage("Produit mis à jour avec succès");
                ad.setPositiveButton(
                    "OK",
                    DialogInterface.OnClickListener { dialogInterface, i ->
                        intent = Intent(this, ProductsList::class.java);
                        intent.putExtra("category", proCategory );
                        startActivity(intent);
                    });
                ad.show();
            } */

        }
    }


    override fun onActivityResult(requestCode : Int  , resultCode : Int , data : Intent?){
        super.onActivityResult(requestCode , resultCode , data)
        if (requestCode == 100 && resultCode == RESULT_OK){
            val uri = data?.data
            val inputStream = contentResolver.openInputStream(uri!!)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            upProductImage.setImageBitmap(bitmap)
        }
    }

    private fun getBytes(bitmap : Bitmap) : ByteArray{
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG , 0 , stream);
        return  stream.toByteArray()
    }
}