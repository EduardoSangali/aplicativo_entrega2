package com.example.appteste1.model

data class Procedimento(
    val nome: String,
    var descricao: String
){
    constructor() : this("","")
}
