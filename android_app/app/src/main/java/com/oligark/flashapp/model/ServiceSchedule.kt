package com.oligark.flashapp.model

import java.util.*

data class ServiceSchedule(
        var id:Long?=null,
        var seller: User?=null,
        var type: ServiceCategory?=null,
        var startTime: Date?=null,
        var endTime: Date?=null,
        var description : String?=null
) {
}