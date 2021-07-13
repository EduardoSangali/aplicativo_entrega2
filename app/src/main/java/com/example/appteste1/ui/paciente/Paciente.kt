package com.example.appteste1.ui.paciente

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Paciente(
    val nome: String,
    var idade: String,
    var telefone: String,
    var email: String,
    val documento: String
)
