package com.example.appteste1.model.bean

data class Agendamento(
    val id: String,
    val dataHora: String,
    val procedim: String,
    val idPaciente: String,
    var profissional: String,
    var infoId: Int,
    var editId: Int?,
    var delId: Int?
){
    constructor() : this("","", "", "","", 0,0,0)
}
