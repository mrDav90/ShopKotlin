package com.example.shopappkotlin.data

data class Product(

    var pName : String ,
    var pPrice : String ,
    var pDescription : String ,
    var pCategory :  String ,
    var pImage : ByteArray
) {
    var pId : Int = -1
    constructor( pId : Int , pName: String , pPrice: String , pDescription: String , pCategory: String , pImage: ByteArray):this(pName , pPrice , pDescription , pCategory , pImage){
        this.pId = pId
    }
}
