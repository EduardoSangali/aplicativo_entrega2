package com.example.appteste1.ui.appointment

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.appteste1.R
import com.example.appteste1.model.bean.Agendamento
import com.example.appteste1.ui.professional.ProfessionalViewModel
import com.example.appteste1.ui.procedure.ProcedureViewModel
import java.text.SimpleDateFormat
import java.util.*

class SaveAppointmentFragment : Fragment() {

    private lateinit var adapter: AppointmentListAdapter
    private lateinit var viewModel: AppointmentViewModel
    private lateinit var procedureViewModel: ProcedureViewModel
    private lateinit var professionalViewModel: ProfessionalViewModel

    val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")

    /* *
     * This method creates the view
     * We will just inflate the layout to have the main view loaded
     */
    override fun onCreateView(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("SAVE APPNTMNT FRAGMEN", "onCreateView -> START")
        return layoutInflater.inflate(R.layout.fragment_saveappointment, container, false);
    }

    /**
     * After the view is created we invoke this method to populate any data
     *
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        Log.i("SAVE APPNTMNT FRAGMENT", "VIEW CREATED. POPULATING DATA...")
        viewModel =
            ViewModelProvider(this).get(AppointmentViewModel::class.java)

        procedureViewModel =
            ViewModelProvider(this).get(ProcedureViewModel::class.java)

        professionalViewModel =
            ViewModelProvider(this).get(ProfessionalViewModel::class.java)

        val appointmentID = arguments?.getString("appointmentID")
        Log.i("SAVE APPNTMNT FRAGMENT", "appointmentID: "+appointmentID)

        //Get buttons
        val mPickTimeBtn : ImageView = view.findViewById(R.id.pickDateBtn)
        val cancelBtn : Button = view.findViewById(R.id.deletar)
        val saveBtn : Button = view.findViewById(R.id.enviar)

        //Get the back icon
        val backIcon : ImageView = view.findViewById(R.id.backIcon)

        // Get form fields
        var dataHora : TextView = view.findViewById(R.id.mostraDataHora)
        var procedureList : Spinner = view.findViewById(R.id.spinner2)
        var professionalList : Spinner = view.findViewById(R.id.spinner3)

        // Add an observer to the appointment so the form is updated
        viewModel.appointment.observe(viewLifecycleOwner, Observer {
            dataHora.text = it.dataHora
            setSelectValue(procedureList, it.procedim)
            setSelectValue(professionalList, it.profissional)
        })

        procedureViewModel.procedureList.observe(viewLifecycleOwner, Observer{
            val arrayAdapter = ArrayAdapter(requireContext(),
                R.layout.color_spinner_layout,
                it)
                .also { adapter ->
                    // Specify the layout to use when the list of choices appears
                    adapter.setDropDownViewResource(R.layout.color_spinner_dropdown_layout)
                    // Apply the adapter to the spinner
                    procedureList.adapter = adapter
                }
        })

        professionalViewModel.professionalList.observe(viewLifecycleOwner, Observer {
            val arrayAdapter = ArrayAdapter(requireContext(),
                R.layout.color_spinner_layout,
                it)
                .also { adapter ->
                    // Specify the layout to use when the list of choices appears
                    adapter.setDropDownViewResource(R.layout.color_spinner_dropdown_layout)
                    // Apply the adapter to the spinner
                    professionalList.adapter = adapter
                }
        })

        procedureViewModel.loadProcedures(procedureList, requireContext())
        professionalViewModel.loadProfessionals(professionalList, requireContext())
        loadDateTime(mPickTimeBtn, dataHora)

        // if appointment ID is not null we are on the update screen
        if(appointmentID != null){
            viewModel.getAppointment(appointmentID)
        }

        saveBtn.setOnClickListener (){
            var dateStr = dataHora.text.toString()
            var date = sdf.parse(dateStr)


            //setting the timestamp as the id of the appointment
            var appointment = Agendamento(Calendar.getInstance().timeInMillis.toString(),
                dataHora.text.toString(),
                procedureList.selectedItem.toString(),
                "", //TODO - Need to get the logged user
                professionalList.selectedItem.toString(),
                date.time.toString()
            )

            if(appointmentID != null){
                appointment.id = appointmentID
                viewModel.updateAppointment(appointment, view, requireContext())
            }else{
                viewModel.insertAppointment(appointment, view, requireContext())
            }
        }

        cancelBtn.setOnClickListener(){
            procedureList.setAdapter(null)
            professionalList.setAdapter(null)
            dataHora.setText("")
        }

        backIcon.setOnClickListener(View.OnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigation_appointments)
        })

        Log.i("SAVE APPNTMNT FRAGMENT", "VIEW CREATED. DATA POPULATED.")
    }

    private fun setSelectValue(selectBox: Spinner, value : String){
        val adapter = selectBox.adapter
        val size = adapter.count
        for(i in 0 until size){
            if(value == adapter.getItem(i)){
                selectBox.setSelection(i)
                break
            }
        }
    }

    private fun loadDateTime(mPickTimeBtn : ImageView, dateTimeField : TextView){
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

            // set the minimum date to today, the past dates are blocked
            dpd.datePicker.minDate = System.currentTimeMillis() - 1000


            val dph = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                c.set(Calendar.HOUR_OF_DAY, hour)
                c.set(Calendar.MINUTE, minute)
                dateTimeField.append(SimpleDateFormat(" HH:mm").format(c.time))
            }
            TimePickerDialog(requireContext(), dph, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show()

            dpd.show()

        }
    }
}
