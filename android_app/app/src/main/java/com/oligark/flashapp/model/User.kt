package com.oligark.flashapp.model

import java.util.Date

data class User(
        val id:Long?=null,
        val firstName: String?=null,
        val firstSurname: String?=null,
        val secondSurname: String?=null,
        val birthday: Date?=null,
        val address: String?=null,
        val email: String?=null,
        val imgUrl: String?=null,
        val sellerDescription: String? = null,
        val sellerRating: Int? = null,
        val customerRating: Int? = null,
        val password: String? = null,
        val googleToken: String? = null,
        val fbToken: String? = null
) {
}