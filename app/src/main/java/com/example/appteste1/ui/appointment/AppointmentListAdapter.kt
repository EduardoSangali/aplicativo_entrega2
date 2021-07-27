package com.example.appteste1.ui.appointment

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.appteste1.R
import com.example.appteste1.model.bean.Agendamento
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.lang.StringBuilder

class AppointmentListAdapter (var ctx:Context,
                              var resource: Int,
                              var list: ArrayList<Agendamento>,
                              var hasInfo: Boolean,
                              var hasEdit: Boolean,
                              var hasDel: Boolean,
                              var viewModel: AppointmentViewModel):  ArrayAdapter<Agendamento>(ctx, resource, list) {


    private lateinit var database: DatabaseReference

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater: LayoutInflater = LayoutInflater.from(ctx)
        val view: View = layoutInflater.inflate(resource, null)

        //Get items
        val dataProced: TextView = view.findViewById<TextView>(R.id.listaDataHora)
        val nomeProced: TextView = view.findViewById<TextView>(R.id.listaEspecialidade2)
        val infoProced: ImageView = view.findViewById<ImageView>(R.id.listInfo)
        val editProced: ImageView = view.findViewById<ImageView>(R.id.listEdit)
        val delProced: ImageView = view.findViewById<ImageView>(R.id.listDelete)

        //Set items
        dataProced.text = list[position].dataHora
        nomeProced.text = list[position].procedim

        val appointmentID = list[position].id

        database = Firebase.database.reference

        if(hasInfo) {
            //TODO - mostra as informações mas o layout está muito sujo
            infoProced.setOnClickListener(){
                database.child("agendamentos").child(appointmentID).get().addOnSuccessListener {
                    val genericTypeIndicator: GenericTypeIndicator<Agendamento> =
                        object : GenericTypeIndicator<Agendamento>() {}

                    var appointment : Agendamento? = it.getValue(genericTypeIndicator)

                    var message = StringBuilder()
                    if(appointment != null) {
                        message.append("Seu atendimento está agendado para \n")
                        message.append(appointment.dataHora + "\n")
                        message.append("com o profissional "+ appointment.profissional + "\n")
                        message.append("para a realização de " + appointment.procedim)
                    }

                    val builder = AlertDialog.Builder(ctx)
                    builder.setTitle("Agendamento")
                    builder.setMessage(message.toString())
                        .setCancelable(false)
                        .setPositiveButton("OK") { dialog, id -> dialog.dismiss() }
                    val alert = builder.create()
                    alert.show()
                }
            }
        }else{
            infoProced.visibility = View.GONE
        }

        if(hasEdit) {
            editProced.setOnClickListener(){
                //redirect to insert/update form passing the appointment ID as argument
                val bundle = bundleOf("appointmentID" to appointmentID)
                Navigation.findNavController(view).navigate(R.id.navigation_save_appointment, bundle)
            }
        }else{
            editProced.visibility = View.GONE
        }

        if(hasDel) {
            delProced.setOnClickListener(){
                val builder = AlertDialog.Builder(ctx)
                builder.setTitle("Agendamento")
                builder.setMessage("Cancelar Agendamento?")
                    .setCancelable(true)
                    .setPositiveButton("OK") { dialog, id ->
                        dialog.dismiss()
                        viewModel.deleteAppointment(appointmentID, ctx)
                    }
                    .setNegativeButton("Cancel") { dialog, id ->
                        dialog.cancel()
                    }
                val alert = builder.create()
                alert.show()
            }
        }else{
            delProced.visibility = View.GONE
        }

        return view
    }
}