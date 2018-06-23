
package com.oligark.flashapp.model

import java.util.*

data class Service(
        val id: Long?=null,
        val date: Date?=null,
        val comments: String?=null,
        val status: ServiceStatus?=null
) {
}