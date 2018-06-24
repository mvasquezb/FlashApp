

package com.oligark.flashapp.model

import java.util.*

data class Service(
        val idVendedor: Long?=null,
        val tipoServicio: String?=null,
        val horaInicio: String?=null,
        val horaFin: String?=null,
        val fechaInicio: String?=null,
        val fechaFin: String?=null,
        val descripcion: String?=null
) {
}
