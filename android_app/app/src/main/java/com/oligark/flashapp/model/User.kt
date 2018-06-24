package com.oligark.flashapp.model

import java.util.Date

data class User(
        var id:Long?=null,
        var firstName: String?=null,
        var lastName: String?=null,
        var birthday: Date?=null,
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
}