package com.example.appteste1.model.bean

data class Paciente(
    val id: String,
    var nome: String,
    var email: String
){
    constructor() : this("","", "")
}
