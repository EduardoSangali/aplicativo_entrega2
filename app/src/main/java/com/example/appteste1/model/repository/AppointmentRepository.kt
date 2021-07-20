package com.example.appteste1.model.repository

import android.util.Log
import com.example.appteste1.model.bean.Agendamento
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AppointmentRepository() {
    private lateinit var database: DatabaseReference

    fun insertAppointment(appointment: Agendamento){
        // get a reference of the database object
        database = Firebase.database.reference

        database.child("agendamentos").child(appointment.id).setValue(appointment)
    }

    fun getAppointment(id: String): Agendamento? {
        database = Firebase.database.reference

        val result : DataSnapshot? = database.child("agendamentos").child(id).get().result

        //create a generic type to be used to extract the data from Firebase
        val genericTypeIndicator: GenericTypeIndicator<Agendamento> =
            object : GenericTypeIndicator<Agendamento>() {}

        var appointment : Agendamento? = result?.getValue(genericTypeIndicator)

        return appointment
    }

    fun getAllAppointments(): List<Agendamento>? {
        val allAppointments = ArrayList<Agendamento>()

        database = Firebase.database.reference

        val result : DataSnapshot? = database.child("agendamentos").get().result

        //create a generic type to be used to extract the data from Firebase
        val genericTypeIndicator: GenericTypeIndicator<Map<String, Agendamento>> =
            object : GenericTypeIndicator<Map<String, Agendamento>>() {}

        //The getValue method will convert the JSON string to a hashmap
        val hashMap: Map<String, Agendamento>? = result?.getValue(genericTypeIndicator)
        if (hashMap != null) {
            //loop over the map items and add
            for ((_, appointment) in hashMap) {
                allAppointments.add(appointment)
            }
        }

        return allAppointments
    }
}