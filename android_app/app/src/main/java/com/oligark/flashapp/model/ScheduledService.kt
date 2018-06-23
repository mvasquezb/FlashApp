package com.oligark.flashapp.model

import java.util.*

data class ScheduledService(
        val id:Long?=null,
        val seller: User?=null,
        val type: ServiceCategory?=null,
        val startTime: Date?=null,
        val endTime: Date?=null,
        val description : String?=null
) {
}