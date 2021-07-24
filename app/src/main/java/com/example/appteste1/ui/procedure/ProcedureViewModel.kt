package com.example.appteste1.ui.procedure

import android.content.Context
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appteste1.R
import com.example.appteste1.model.bean.Agendamento
import com.example.appteste1.model.bean.Procedimento
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ProcedureViewModel : ViewModel() {
    private lateinit var database: DatabaseReference

    val procedureList = MutableLiveData<ArrayList<String>>()
    private val nonMutableProcedureList = ArrayList<String>()

    // The observer does not recognize adding an item to list inside the
    // MutableLiveDate as changing the object. We need to actually replace
    // the object
    private fun refreshMutableList() {
        procedureList.value = nonMutableProcedureList
    }

    fun loadProcedures(proceduresSelectBox : Spinner, ctx : Context){
        // get a reference of the database object
        database = Firebase.database.reference

        database.child("procedimentos").get().addOnSuccessListener{
            Log.i("firebase", "Value: " + it.value)

            nonMutableProcedureList.add("Selecione um procedimento")

            //create a generic type to be used to extract the data from Firebase
            val genericTypeIndicator: GenericTypeIndicator<List<Procedimento>> =
                object : GenericTypeIndicator<List<Procedimento>>() {}

            //The getValue method will convert the JSON string to a list
            val list: List<Procedimento>? = it.getValue(genericTypeIndicator)
            if (list != null) {
                for (procedure in list){
                    nonMutableProcedureList.add(procedure.nome)
                }
                refreshMutableList()
            }
        }
    }
}