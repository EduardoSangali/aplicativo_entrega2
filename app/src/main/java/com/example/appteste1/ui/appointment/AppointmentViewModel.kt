package com.example.appteste1.ui.appointment

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.appteste1.R
import com.example.appteste1.model.bean.Agendamento
import com.example.appteste1.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

class AppointmentViewModel : ViewModel(){
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    val appointment = MutableLiveData<Agendamento>()
    private lateinit var _appointment : Agendamento

    val appointmentList = MutableLiveData<ArrayList<Agendamento>>()
    private val nonMutableAppointmentsList = ArrayList<Agendamento>()

    // The observer does not recognize adding an item to list inside the
    // MutableLiveDate as changing the object. We need to actually replace
    // the object
    private fun refreshMutableList() {
        appointmentList.value = nonMutableAppointmentsList
    }

    fun loadAppointmentsList(context : Context){
        val currentDate = Date().time.toString()

        // get a reference of the database object
        database = Firebase.database.reference
        database.child("agendamentos")
            .orderByChild("dataHoraTS").startAt(currentDate).get().addOnSuccessListener {
                Log.i("firebase", "Value: " + it.value)

                //create a generic type to be used to extract the data from Firebase
                val genericTypeIndicator: GenericTypeIndicator<Map<String, Agendamento>> =
                    object : GenericTypeIndicator<Map<String, Agendamento>>() {}

                //The getValue method will convert the JSON string to a hashmap
                val hashMap: Map<String, Agendamento>? = it.getValue(genericTypeIndicator)
                if (hashMap != null) {
                    //loop over the map items and add
                        Log.i("VIEWMODEL", "Add appointments to mutable list")
                    for ((_, agendamento) in hashMap) {
                        nonMutableAppointmentsList.add(agendamento)
                    }
                    refreshMutableList()
                }
            }
    }

    fun loadAppointmentsHistory(context : Context){
        val currentDate = Date().time.toString()

        // get a reference of the database object
        database = Firebase.database.reference
        database.child("agendamentos")
            .orderByChild("dataHoraTS").endAt(currentDate).get().addOnSuccessListener{
                Log.i("firebase", "Value: " + it.value)

                //create a generic type to be used to extract the data from Firebase
                val genericTypeIndicator: GenericTypeIndicator<Map<String, Agendamento>> =
                    object : GenericTypeIndicator<Map<String, Agendamento>>() {}

                //The getValue method will convert the JSON string to a hashmap
                val hashMap: Map<String, Agendamento>? = it.getValue(genericTypeIndicator)
                if (hashMap != null) {
                    //loop over the map items and add
                    Log.i("VIEWMODEL", "Add appointments to mutable list")
                    for ((_, agendamento) in hashMap) {
                        nonMutableAppointmentsList.add(agendamento)
                    }
                    refreshMutableList()
                }
            }
    }

    fun getAppointment(appointmentID: String) {
        // get a reference of the database object
        database = Firebase.database.reference
        database.child("agendamentos").child(appointmentID).get().addOnSuccessListener {
            val genericTypeIndicator: GenericTypeIndicator<Agendamento> =
                object : GenericTypeIndicator<Agendamento>() {}

            var appointmentFromDB: Agendamento? = it.getValue(genericTypeIndicator)
            if (appointmentFromDB != null) {
                appointment.value = appointmentFromDB!!
            }
        }
    }

    fun insertAppointment(appointment: Agendamento, view: View, ctx: Context){
        // get a reference of the database object
        database = Firebase.database.reference
        database.child("agendamentos").child(appointment.id).setValue(appointment)
            .addOnSuccessListener {
                Log.i("firebase","Successfully inserted.")
                showAlert("Agendamento de Consulta", "Agendamento Realizado!", "OK", ctx)
                Navigation.findNavController(view).navigate(R.id.navigation_appointments)
            }
            .addOnFailureListener {
                Log.e("firebase", "Error writing data", it)
                showAlert("Agendamento de Consulta", "Não foi possível agendar consulta.", "OK", ctx)
            }
    }

    fun updateAppointment(appointment: Agendamento, view: View, ctx: Context){
        val map = appointment.toMap()

        // get a reference of the database object
        database = Firebase.database.reference
        database.child("agendamentos").child(appointment.id).updateChildren(map as Map<String, Any>)
            .addOnSuccessListener {
                Log.i("firebase","Successfully inserted.")
                showAlert("Agendamento de Consulta", "Alteração Realizada!", "OK", ctx)
                Navigation.findNavController(view).navigate(R.id.navigation_appointments)
            }
            .addOnFailureListener {
                Log.e("firebase", "Error writing data", it)
                showAlert("Agendamento de Consulta", "Não foi possível alterar agendamento.", "OK", ctx)
            }
    }

    fun deleteAppointment(appointmentID : String, ctx : Context){
        // get a reference of the database object
        database = Firebase.database.reference
        database.child("agendamentos").child(appointmentID).removeValue().addOnSuccessListener {
            Toast.makeText(ctx, "Agendamento cancelado.", Toast.LENGTH_SHORT).show()
            for(appointment in nonMutableAppointmentsList){
                if(appointment.id == appointmentID){
                    nonMutableAppointmentsList.remove(appointment)
                    break
                }
            }
            refreshMutableList()
        }
    }

    fun logout(view: View) {
        auth = FirebaseAuth.getInstance()
        auth.signOut();
    }

    private fun showAlert(title: String, message: String, buttonText: String = "OK", ctx: Context) {
        val builder =
            AlertDialog.Builder(ctx)
        builder.setTitle(title)
        builder.setMessage(message)
            .setCancelable(false)
            .setPositiveButton(buttonText) { dialog, id ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }
}