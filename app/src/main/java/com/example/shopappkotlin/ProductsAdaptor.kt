package com.example.shopappkotlin

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.shopappkotlin.data.Product

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

        tvName.text = product.pName
        tvPrice.text = product.pPrice
        tvDescription.text = product.pDescription
        tvCategory.text = product.pCategory
        val bitmap = getBitmap(product.pImage)
        tvPicture.setImageBitmap(bitmap);
        return itemView
    }


    fun getBitmap(byteArray: ByteArray ) : Bitmap {
        val bitmap = BitmapFactory.decodeByteArray(byteArray , 0 , byteArray.size)
        return bitmap
    }
}
