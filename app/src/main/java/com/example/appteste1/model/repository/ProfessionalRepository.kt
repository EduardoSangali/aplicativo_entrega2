package com.example.appteste1.model.repository

import com.example.appteste1.model.bean.Profissional
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ProfessionalRepository () {
    private lateinit var database: DatabaseReference

    fun insertProfessional(professional: Profissional){
        // get a reference of the database object
        database = Firebase.database.reference

        database.child("profissionais").setValue(professional)
    }

    fun getAllProfessionals(): List<Profissional>? {
        val allProfessionals = ArrayList<Profissional>()

        database = Firebase.database.reference

        val result : DataSnapshot? = database.child("profissionais").get().result

        //create a generic type to be used to extract the data from Firebase
        val genericTypeIndicator: GenericTypeIndicator<List<Profissional>> =
            object : GenericTypeIndicator<List<Profissional>>() {}

        //The getValue method will convert the JSON string to a list
        val list: List<Profissional>? = result?.getValue(genericTypeIndicator)
        if (list != null) {
            for (professional in list){
                allProfessionals.add(professional)
            }
        }

        return allProfessionals
    }
}