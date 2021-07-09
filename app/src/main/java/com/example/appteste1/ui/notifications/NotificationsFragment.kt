package com.example.appteste1.ui.notifications

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.appteste1.R
import com.example.appteste1.databinding.FragmentNotificationsBinding
import java.text.SimpleDateFormat
import java.util.*

class NotificationsFragment : Fragment() {
    lateinit var menu: Spinner

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

       /* val textView: TextView = binding.txtDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        val spinner: Spinner = binding.spinner2
        val spinner3: Spinner = binding.spinner3
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.especialidades,
            R.layout.color_spinner_layout,

        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.color_spinner_dropdown_layout)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }


        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.profissional,
            R.layout.color_spinner_layout
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.color_spinner_dropdown_layout)
            // Apply the adapter to the spinner
            spinner3.adapter = adapter
        }


        val mPickTimeBtn = binding.pickDateBtn
        val cancelBtn = binding.deletar

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val hour = c.get(Calendar.HOUR)
        val minute = c.get(Calendar.MINUTE)

        mPickTimeBtn.setOnClickListener {

            val dpd = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView
                binding.mostraDataHora.text = ("" + dayOfMonth + "/" + month + "/" + year)
            }, year, month, day )


            val dph = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                c.set(Calendar.HOUR_OF_DAY, hour)
                c.set(Calendar.MINUTE, minute)
                binding.mostraDataHora.append(SimpleDateFormat(" HH:mm").format(c.time))
            }
            TimePickerDialog(requireContext(), dph, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show()

            dpd.show()

        }

        cancelBtn.setOnClickListener(){
            binding.spinner2.setAdapter(null)
            binding.spinner3.setAdapter(null)
            binding.mostraDataHora.setText("")
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}