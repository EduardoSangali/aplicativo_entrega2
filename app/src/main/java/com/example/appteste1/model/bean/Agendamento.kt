package com.example.appteste1.model.bean

data class Agendamento(
    val id: String,
    val dataHora: String,
    val procedim: String,
    val idPaciente: String,
    var profissional: String,
    val dataHoraTS: String
){
    constructor() : this("","", "", "","", "")
}
