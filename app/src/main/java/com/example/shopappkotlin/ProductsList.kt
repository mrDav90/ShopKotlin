package com.example.shopappkotlin

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import android.widget.SearchView
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import com.example.shopappkotlin.data.Product
import com.example.shopappkotlin.db.Database

class ProductsList : AppCompatActivity() {
    lateinit var db : Database
    lateinit var rs : Cursor
    lateinit var adapter : SimpleCursorAdapter
    lateinit var adaptor: ProductsAdaptor
    lateinit var listProduct : ListView
    var productArray = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_list)
        db = Database(applicationContext)
        val bundle: Bundle? = intent.extras
        val category = bundle?.get("currentCategory");
        var searchProduct = findViewById<SearchView>(R.id.searchProduct);
        listProduct = findViewById(R.id.listProduct);
        searchProduct.visibility = View.VISIBLE
        listProduct.visibility = View.VISIBLE

        productArray = db.findProducts(category.toString())
        adaptor = ProductsAdaptor (this , R.layout.item_product , productArray)
        listProduct.adapter = adaptor
        registerForContextMenu(listProduct)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu , menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.addIcon -> {
                Toast.makeText(this , "add test" , Toast.LENGTH_SHORT).show()
            }
            R.id.paramsIcon -> {
                Toast.makeText(this , "params test" , Toast.LENGTH_SHORT).show()
            }
            R.id.disconnectIcon -> {
                Toast.makeText(this , "disconnect test" , Toast.LENGTH_SHORT).show()
            }
        }

        return super.onOptionsItemSelected(item)
    }


}