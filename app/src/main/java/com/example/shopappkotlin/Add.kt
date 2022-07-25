package com.example.shopappkotlin

import android.content.ClipDescription
import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.example.shopappkotlin.data.Product
import com.example.shopappkotlin.db.Database
import java.io.ByteArrayOutputStream

class Add : AppCompatActivity() {
    var bitmap : Bitmap? = null
    lateinit var db : Database
    lateinit var edProductImage : ImageButton
    lateinit var edProductName : EditText
    lateinit var edProductPrice : EditText
    lateinit var edProductDescription : EditText
    lateinit var addProductButton : Button
    lateinit var listProductButton:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        db = Database(this)
        edProductName = findViewById(R.id.edProductName);
        edProductPrice = findViewById(R.id.edProductPrice);
        edProductDescription = findViewById(R.id.edProductDescription);
        edProductImage = findViewById(R.id.edProductImage);
        addProductButton = findViewById(R.id.addProductButton);
        listProductButton = findViewById(R.id.listProductButton);
        var addTitle = findViewById<TextView>(R.id.addTitle);
        val bundle: Bundle? = intent.extras
        val category = bundle?.get("currentCategory")

        val galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { data ->
            val inputStream = contentResolver.openInputStream(data)
            bitmap = BitmapFactory.decodeStream(inputStream)
            edProductImage.setImageBitmap(bitmap);
        }

        edProductImage.setOnClickListener{
            // just for open the gallery
            galleryLauncher.launch("image/*");
        }

        addTitle.setText("Ajout d'un produit (${category}) ");
        addProductButton.setOnClickListener {
            var ad = AlertDialog.Builder(this);
            val proName = edProductName.text.toString();
            val proPrice = edProductPrice.text.toString();
            val proDescription = edProductDescription.text.toString();
            val proCategory = category.toString();
            val proImageBlob : ByteArray = getBytes(bitmap!!);

            if (proName.isEmpty() || proPrice.isEmpty() || proDescription.isEmpty() || proCategory.isEmpty() || proImageBlob == null ){
                Toast.makeText(this , "Veuillez svp renseigner les données dans les champs" , Toast.LENGTH_SHORT).show()
            }
            else
            {

                val product = Product(proName , proPrice , proDescription , proCategory , proImageBlob)
                val result = db.addProduct(product)

                if (result){

                    var ad = AlertDialog.Builder(this);
                    ad.setTitle("Succès");
                    ad.setMessage("Produit ajouté avec succès");
                    ad.setPositiveButton("OK" , DialogInterface.OnClickListener { dialogInterface, i ->
                        edProductName.setText("");
                        edProductPrice.setText("");
                        edProductDescription.setText("");
                        bitmap = null
                        edProductName.requestFocus();
                    });
                    ad.show();
                }

            }

        }

        listProductButton.setOnClickListener {
            val proCategory = category.toString() ;
            intent = Intent(this , ProductsList::class.java);
            intent.putExtra("category" , proCategory  );
            startActivity(intent);
        }
    }

    override fun onActivityResult(requestCode : Int  , resultCode : Int , data : Intent?){
        super.onActivityResult(requestCode , resultCode , data)
        if (requestCode == 100 && resultCode == RESULT_OK){
            val uri = data?.data
            val inputStream = contentResolver.openInputStream(uri!!)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            edProductImage.setImageBitmap(bitmap)
        }
    }

    private fun getBytes(bitmap : Bitmap) : ByteArray{
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG , 0 , stream);
        return  stream.toByteArray()
    }


}