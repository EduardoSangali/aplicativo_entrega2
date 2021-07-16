package com.example.appteste1.model

data class Agendamento(
    val id: String,
    val dataHora: String,
    val procedim: String,
    var profissional: String,
    var infoId: Int,
    var editId: Int?,
    var delId: Int?
){
    constructor() : this("","", "", "", 0,0,0)
}
