package com.oligark.flashapp.model

data class User(
        var id:Long?=null,
        var firstName: String?=null,
        var firstSurname: String?=null,
        var secondSurname: String?=null,
        var age: Int?=null,
        var address: String?=null,
        var email: String?=null,
        var imgUrl: String?=null,
        var sellerDescription: String? = null,
        var sellerRating: Int? = null,
        var customerRating: Int? = null,
        var password: String? = null,
        var googleToken: String? = null,
        var fbToken: String? = null
) {
    override fun toString(): String {
        return "$firstName $firstSurname $secondSurname"
    }
}