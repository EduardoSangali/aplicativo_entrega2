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
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import com.example.appteste1.R
import com.example.appteste1.model.bean.Agendamento
import com.example.appteste1.ui.contacts.ContactsFragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import androidx.fragment.app.DialogFragment
import java.lang.StringBuilder

class AppointmentListAdapter (var ctx:Context,
                              var resource: Int,
                              var list: ArrayList<Agendamento>,
                              var hasInfo: Boolean,
                              var hasEdit: Boolean,
                              var hasDel: Boolean,
                              var isHistory: Boolean,
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
                        if(isHistory){
                            message.append("Seu atendimento foi realizado dia "+appointment.dataHora.substring(0,9) )
                            message.append(" às "+appointment.dataHora.substring(10) )
                            message.append(" com o profissional "+ appointment.profissional + "\n")
                            message.append("para a realização de " + appointment.procedim)
                        }
                        else{

                            message.append("Data: " +appointment.dataHora.substring(0,9) + "\n")
                        message.append("Hora: " +appointment.dataHora.substring(10) + "\n")
                        message.append("Profissional: "+ appointment.profissional + "\n")
                        message.append("Procedimento: " + appointment.procedim)}
                    }


                    val builder = AlertDialog.Builder(ctx, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
                    builder.setTitle("Detalhes")
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