package com.oligark.flashapp.model


class Customer (
        var id: Long? = null,
        var client: User?=null,
        var service: Service?=null,
        var qualificationSeller: String?=null,
        var commentSeller: String?=null,
        var qualificationClient: String?=null,
        var commentClient: String?=null

)

{

}