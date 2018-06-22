package com.oligark.flashapp.model


class Customer (
        val id: Long? = null,
        val client: User?=null,
        val service: Service?=null,
        val qualificationSeller: String?=null,
        val commentSeller: String?=null,
        val qualificationClient: String?=null,
        val commentClient: String?=null

)

{

}