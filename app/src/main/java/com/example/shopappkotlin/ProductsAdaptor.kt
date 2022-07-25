package com.example.shopappkotlin

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.shopappkotlin.data.Product
import com.example.shopappkotlin.db.Database

class ProductsAdaptor (
    var myContext : Context,
    var ressource : Int,
    var values : ArrayList<Product>
) : ArrayAdapter<Product>(myContext , ressource , values) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val product = values[position]
        val itemView = LayoutInflater.from(myContext).inflate(ressource , parent , false)
        val tvName = itemView.findViewById<TextView>(R.id.name)
        val tvPrice = itemView.findViewById<TextView>(R.id.price)
        val tvDescription = itemView.findViewById<TextView>(R.id.description)
        val tvCategory = itemView.findViewById<TextView>(R.id.category)
        val tvPicture = itemView.findViewById<ImageView>(R.id.imageView)
        val detailsIcon = itemView.findViewById<ImageView>(R.id.detailsIcon)

        tvName.text = product.pName
        tvPrice.text = product.pPrice
        tvDescription.text = product.pDescription
        tvCategory.text = product.pCategory
        val bitmap = getBitmap(product.pImage)
        tvPicture.setImageBitmap(bitmap);


        detailsIcon.setOnClickListener{
            val popupMenu = PopupMenu(myContext , detailsIcon)
            popupMenu.menuInflater.inflate(R.menu.list_popup_menu,popupMenu.menu)

            popupMenu.setOnMenuItemClickListener { item ->
                when(item.itemId){
                    R.id.itemDetails -> {
                        Intent(myContext , ProductDetails::class.java).also{
                            it.putExtra("productName" , product.pName)
                            it.putExtra("productPrice" , product.pPrice)
                            it.putExtra("productDescription" , product.pDescription)
                            it.putExtra("productCategory" , product.pCategory)
                            it.putExtra("productImage" , product.pImage)
                            myContext.startActivity(it)
                        }
                    }
                    R.id.itemUpdate -> {
                        Intent(myContext , ProductUpdate::class.java).also{
                            it.putExtra("productName" , product.pName)
                            it.putExtra("productPrice" , product.pPrice)
                            it.putExtra("productDescription" , product.pDescription)
                            it.putExtra("productCategory" , product.pCategory)
                            it.putExtra("productImage" , product.pImage)
                            myContext.startActivity(it)
                        }
                    }
                    R.id.itemDelete -> {
                        val db = Database(myContext)
                        val result = db.deleteProduct(product.pId)
                        if (result){
                            values.removeAt(position)
                            notifyDataSetChanged()
                        }
                        else{
                            Toast.makeText(myContext , "Quelque chose s'est mal passée" , Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                true
            }
        }


        return itemView
    }


    fun getBitmap(byteArray: ByteArray ) : Bitmap {
        val bitmap = BitmapFactory.decodeByteArray(byteArray , 0 , byteArray.size)
        return bitmap
    }
}
