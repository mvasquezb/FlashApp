package com.oligark.flashapp.model

import java.time.DayOfWeek
import java.time.Month

class ServiceScheduleDetail (
    val id: Long? = null,
    val maxNumOc: Integer?=null,
    val dayOfWeek: DayOfWeek?=null,
    val weekOfMonth: Integer?=null,
    val month: Month?=null,
    val ScheduleService : ScheduledService?=null

)

{

}