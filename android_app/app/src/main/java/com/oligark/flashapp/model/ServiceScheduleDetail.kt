package com.oligark.flashapp.model

import java.time.DayOfWeek
import java.time.Month

class ServiceScheduleDetail (
    var id: Long? = null,
    var maxNumOc: Integer?=null,
    var dayOfWeek: DayOfWeek?=null,
    var weekOfMonth: Integer?=null,
    var month: Month?=null,
    var scheduleServiceSchedule : ServiceSchedule?=null

)

{

}