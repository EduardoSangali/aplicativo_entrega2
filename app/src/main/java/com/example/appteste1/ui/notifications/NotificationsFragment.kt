package com.example.appteste1.ui.notifications

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.appteste1.R
import com.example.appteste1.databinding.FragmentNotificationsBinding
import com.example.appteste1.model.bean.Agendamento
import com.example.appteste1.model.bean.Procedimento
import com.example.appteste1.model.bean.Profissional
import com.example.appteste1.ui.home.HomeFragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*


class NotificationsFragment : Fragment() {
    lateinit var menu: Spinner

    private lateinit var notificationsViewModel: NotificationsViewModel
    private lateinit var database: DatabaseReference

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        loadProcedures(binding.spinner2)
        loadProfessionals(binding.spinner3)

        val mPickTimeBtn = binding.pickDateBtn
        val cancelBtn = binding.deletar
        val saveBtn = binding.enviar

        loadDateTime(mPickTimeBtn, binding.mostraDataHora)

        val appointmentID = arguments?.getString("appointmentID")
        Log.i("NotificationsFragment", "appointmentID: "+appointmentID)

        // if appointment ID is not null we are on teh update screen
        if(appointmentID != null){
            database.child("agendamentos").child(appointmentID).get().addOnSuccessListener {
                val genericTypeIndicator: GenericTypeIndicator<Agendamento> =
                    object : GenericTypeIndicator<Agendamento>() {}

                var appointment: Agendamento? = it.getValue(genericTypeIndicator)
                if (appointment != null) {
                    binding.mostraDataHora.text = appointment.dataHora
                    setSelectValue(binding.spinner2, appointment.procedim)
                    setSelectValue(binding.spinner3, appointment.profissional)
                }
            }
        }

        saveBtn.setOnClickListener (){
            var dateStr = binding.mostraDataHora.text.toString()
            var date = sdf.parse(dateStr)


            //setting the timestamp as the id of the appointment
            val agendamento = Agendamento(Calendar.getInstance().timeInMillis.toString(),
                binding.mostraDataHora.text.toString(),
                binding.spinner2.selectedItem.toString(),
                "", //TODO - Need to get the logged user
                binding.spinner3.selectedItem.toString(),
                date.time.toString()
            )

            saveAppointment(agendamento)
        }

        cancelBtn.setOnClickListener(){
            binding.spinner2.setAdapter(null)
            binding.spinner3.setAdapter(null)
            binding.mostraDataHora.setText("")
        }

        binding.backIcon.setOnClickListener(View.OnClickListener {
            replaceView(HomeFragment())
        })
        return root
    }

    private fun replaceView(fragment: Fragment) {

        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_host_fragment_activity_main,fragment)
        fragmentTransaction.commit()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun saveAppointment(agendamento: Agendamento){
        database.child("agendamentos").child(agendamento.id).setValue(agendamento)
            .addOnSuccessListener {
                Log.i("firebase","Successfully inserted.")
                showAlert("Agendamento de Consulta", "Agendamento Realizado!")
            }
            .addOnFailureListener {
                Log.e("firebase", "Error writing data", it)
                showAlert("Agendamento de Consulta", "Não foi possível agendar consulta.")
            }
    }

    fun showAlert(title: String, message: String, buttonText: String = "OK") {
        val builder =
            AlertDialog.Builder(requireContext())
        builder.setTitle(title)
        builder.setMessage(message)
            .setCancelable(false)
            .setPositiveButton(buttonText) { dialog, id ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }

    fun loadProcedures(proceduresSelectBox : Spinner){
        // get a reference of the database object
        database = Firebase.database.reference

        database.child("procedimentos").get().addOnSuccessListener{
            Log.i("firebase", "Value: " + it.value)

            val allProcedureNames = ArrayList<String>()
            allProcedureNames.add("Selecione um procedimento")

            //create a generic type to be used to extract the data from Firebase
            val genericTypeIndicator: GenericTypeIndicator<List<Procedimento>> =
                object : GenericTypeIndicator<List<Procedimento>>() {}

            //The getValue method will convert the JSON string to a list
            val list: List<Procedimento>? = it.getValue(genericTypeIndicator)
            if (list != null) {
                for (procedure in list){
                    allProcedureNames.add(procedure.nome)
                }
            }

            val spinner: Spinner = proceduresSelectBox
            val arrayAdapter = ArrayAdapter(requireContext(),
                R.layout.color_spinner_layout,
                allProcedureNames)
                .also { adapter ->
                    // Specify the layout to use when the list of choices appears
                    adapter.setDropDownViewResource(R.layout.color_spinner_dropdown_layout)
                    // Apply the adapter to the spinner
                    spinner.adapter = adapter
                }
        }
    }

    fun loadProfessionals(professionalsSelectBox : Spinner){
        database = Firebase.database.reference
        database.child("profissionais").get().addOnSuccessListener{
            Log.i("firebase", "Value: " + it.value)

            val allProfessionalNames = ArrayList<String>()
            allProfessionalNames.add("Selecione um profissional")

            //create a generic type to be used to extract the data from Firebase
            val genericTypeIndicator: GenericTypeIndicator<List<Profissional>> =
                object : GenericTypeIndicator<List<Profissional>>() {}

            //The getValue method will convert the JSON string to a list
            val list: List<Profissional>? = it.getValue(genericTypeIndicator)
            if (list != null) {
                for (professional in list){
                    allProfessionalNames.add(professional.nome)
                }
            }

            val spinner3: Spinner = professionalsSelectBox
            val arrayAdapter = ArrayAdapter(requireContext(),
                R.layout.color_spinner_layout,
                allProfessionalNames)
                .also { adapter ->
                    // Specify the layout to use when the list of choices appears
                    adapter.setDropDownViewResource(R.layout.color_spinner_dropdown_layout)
                    // Apply the adapter to the spinner
                    spinner3.adapter = adapter
                }

        }
    }

    fun loadDateTime(mPickTimeBtn : ImageView, dateTimeField : TextView){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val hour = c.get(Calendar.HOUR)
        val minute = c.get(Calendar.MINUTE)

        mPickTimeBtn.setOnClickListener {

            val dpd = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView
                //Datepicker return the month index, not its actual value, with the indexes starting at 0
                dateTimeField.text = ("" + dayOfMonth + "/" + (monthOfYear+1) + "/" + year)
            }, year, month, day )


            val dph = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                c.set(Calendar.HOUR_OF_DAY, hour)
                c.set(Calendar.MINUTE, minute)
                dateTimeField.append(SimpleDateFormat(" HH:mm").format(c.time))
            }
            TimePickerDialog(requireContext(), dph, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show()

            dpd.show()

        }
    }

    fun setSelectValue(selectBox: Spinner, value : String){
        val adapter = selectBox.adapter
        val size = adapter.count
        for(i in 0 until (size - 1)){
            if(value.equals(adapter.getItem(i))){
                selectBox.setSelection(i)
            }
        }
    }
}