package com.example.shopappkotlin.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.shopappkotlin.data.Product

class Database(
    myContext: Context ,
    name : String = DB_NAME  ,
    version: Int = DB_VERSION
) : SQLiteOpenHelper(
    myContext ,
    name ,
    null ,
    version

) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createProductTable = """
                CREATE TABLE $PRODUCT_TABLE (
                $productId integer PRIMARY KEY AUTOINCREMENT ,
                $productName text , 
                $productPrice real ,
                $productDescription text ,
                $productCategory text ,
                $productImage blob )
            """.trimIndent()
        db?.execSQL(createProductTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $PRODUCT_TABLE ")
        onCreate(db)
    }

    fun addProduct(product : Product) : Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(productName , product.pName)
        values.put(productPrice , product.pPrice)
        values.put(productDescription , product.pDescription)
        values.put(productCategory , product.pCategory)
        values.put(productImage , product.pImage)
        val result = db.insert(PRODUCT_TABLE , null  ,values).toInt()
        db.close()
        return result !=1
    }

    fun findProducts (cat : String  ) : ArrayList<Product> {
        val product  = ArrayList<Product>()
        val db = this.readableDatabase
        val selectQuery = "SELECT * FROM $PRODUCT_TABLE WHERE productCategory = ? "
        val cursor = db.rawQuery(selectQuery , arrayOf(cat) )
        if (cursor != null ){
            if (cursor.moveToFirst()){
                do {
                    val vId = cursor.getInt(cursor.getColumnIndexOrThrow(productId));
                    val vName = cursor.getString(cursor.getColumnIndexOrThrow(productName));
                    val vPrice = cursor.getString(cursor.getColumnIndexOrThrow(productPrice));
                    val vDescription = cursor.getString(cursor.getColumnIndexOrThrow(
                        productDescription));
                    val vCategory = cursor.getString(cursor.getColumnIndexOrThrow(productCategory));
                    val vImage = cursor.getBlob(cursor.getColumnIndexOrThrow(productImage));
                    val theData = Product(vId , vName , "${vPrice}$" , vDescription , vCategory , vImage)
                    product.add(theData)
                }while (cursor.moveToNext())
            }
        }

        db.close()
        return product
    }


    fun deleteProduct(id : Int) : Boolean {
        val db = writableDatabase
        val result = db.delete(PRODUCT_TABLE , "id=?", arrayOf(id.toString()))
        return result > 0
    }

    // Fix our variables name
    companion object {
         private val DB_NAME ="SHOP_DB"
         private val DB_VERSION = 1
         private val PRODUCT_TABLE = "product"
         private val productId ="_id"
         private val productName ="productName"
         private val productPrice ="productPrice"
         private val productDescription ="productDescription"
         private val productCategory ="productCategory"
         private val productImage ="productImage"
    }
}