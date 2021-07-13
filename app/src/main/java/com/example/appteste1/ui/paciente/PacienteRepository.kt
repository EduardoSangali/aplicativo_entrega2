package com.example.appteste1.ui.paciente

import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class PacienteRepository {
    private lateinit var database: DatabaseReference

    fun initializeDbRef() {
        // [START initialize_database_ref]
        Log.i("firebase", "Init Firebase Database")
        database = Firebase.database.reference
        // [END initialize_database_ref]
        Log.i("firebase", "Init Firebase Database DONE")
    }

    fun insert(paciente: Paciente){
        database.child("pacientes").child(paciente.documento).setValue(paciente)
            .addOnSuccessListener {
                Log.i("firebase", "Successfully inserted")
            }
            .addOnFailureListener {
                Log.e("firebase", "Error writing data", it)
            }
    }

    fun read(id: Long){
        database.child("pacientes").child(id.toString()).get().addOnSuccessListener {
            Log.i("firebase", "Got value ${it.value}")
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }
    }

    fun readAll(){
        database.child("pacientes").get().addOnSuccessListener {
            Log.i("firebase", "Got value ${it.value}")
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }
    }
}