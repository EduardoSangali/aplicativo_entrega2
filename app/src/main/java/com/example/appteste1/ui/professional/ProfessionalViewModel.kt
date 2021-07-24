package com.example.appteste1.ui.professional

import android.content.Context
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appteste1.R
import com.example.appteste1.model.bean.Profissional
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ProfessionalViewModel : ViewModel() {
    private lateinit var database: DatabaseReference

    val professionalList = MutableLiveData<ArrayList<String>>()
    private val nonMutableProfessionalList = ArrayList<String>()

    // The observer does not recognize adding an item to list inside the
    // MutableLiveDate as changing the object. We need to actually replace
    // the object
    private fun refreshMutableList() {
        professionalList.value = nonMutableProfessionalList
    }

    fun loadProfessionals(professionalsSelectBox : Spinner, ctx : Context){
        database = Firebase.database.reference
        database.child("profissionais").get().addOnSuccessListener{
            Log.i("firebase", "Value: " + it.value)

            nonMutableProfessionalList.add("Selecione um profissional")

            //create a generic type to be used to extract the data from Firebase
            val genericTypeIndicator: GenericTypeIndicator<List<Profissional>> =
                object : GenericTypeIndicator<List<Profissional>>() {}

            //The getValue method will convert the JSON string to a list
            val list: List<Profissional>? = it.getValue(genericTypeIndicator)
            if (list != null) {
                for (professional in list){
                    nonMutableProfessionalList.add(professional.nome)
                }
                refreshMutableList()
            }
        }
    }
}