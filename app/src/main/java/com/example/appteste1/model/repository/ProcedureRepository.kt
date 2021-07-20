package com.example.appteste1.model.repository

import com.example.appteste1.model.bean.Procedimento
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ProcedureRepository () {
    private lateinit var database: DatabaseReference

    fun insertProcedure(procedure: Procedimento){
        // get a reference of the database object
        database = Firebase.database.reference

        database.child("procedimentos").setValue(procedure)
    }

    fun getAllProcedures(): List<Procedimento>? {
        val allProcedures = ArrayList<Procedimento>()

        database = Firebase.database.reference

        val result : DataSnapshot? = database.child("procedimentos").get().result

        //create a generic type to be used to extract the data from Firebase
        val genericTypeIndicator: GenericTypeIndicator<List<Procedimento>> =
            object : GenericTypeIndicator<List<Procedimento>>() {}

        //The getValue method will convert the JSON string to a list
        val list: List<Procedimento>? = result?.getValue(genericTypeIndicator)
        if (list != null) {
            for (procedure in list){
                allProcedures.add(procedure)
            }
        }

        return allProcedures
    }
}