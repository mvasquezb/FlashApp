package com.oligark.flashapp.model

class User(
        val firstName: String,
        val firstSurname: String,
        val secondSurname: String,
        val age: Int,
        val address: String,
        val email: String,
        val imgUrl: String,
        val sellerDescription: String? = null,
        val sellerRating: Int? = null,
        val customerRating: Int? = null,
        val password: String? = null,
        val googleToken: String? = null,
        val fbToken: String? = null
) {
}