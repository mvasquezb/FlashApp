

package com.oligark.flashapp.model

import java.util.*

data class Service(
        var id: Long?=null,
        var date: Date?=null,
        var comments: String?=null,
        var status: ServiceStatus?=null,
        var serviceSchedule: ServiceSchedule?=null
) {
}