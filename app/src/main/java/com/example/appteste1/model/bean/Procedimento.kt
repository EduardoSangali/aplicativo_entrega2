package com.example.appteste1.model.bean

data class Procedimento(
    val nome: String,
    var descricao: String
){
    constructor() : this("","")
}
