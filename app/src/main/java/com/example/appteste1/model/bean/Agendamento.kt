package com.example.appteste1.model.bean

import java.util.HashMap

data class Agendamento(
    var id: String,
    val dataHora: String,
    val procedim: String,
    val idPaciente: String,
    var profissional: String,
    val dataHoraTS: String
){
    constructor() : this("","", "", "","", "")

    fun toMap() : Map<String, String>{
        val map = HashMap<String, String>()
        map.put("id", id)
        map.put("dataHora", dataHora)
        map.put("dataHoraTS", dataHoraTS)
        map.put("idPaciente", idPaciente)
        map.put("procedim", procedim)
        map.put("profissional", profissional)

        return map
    }
}
