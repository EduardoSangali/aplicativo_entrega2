package com.example.appteste1.ui.notifications

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.appteste1.R
import com.example.appteste1.databinding.FragmentNotificationsBinding
import com.example.appteste1.ui.home.MyAdapter
import com.example.appteste1.ui.home.Procedimento_
import java.text.SimpleDateFormat
import java.time.Month
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
            android.R.layout.simple_spinner_dropdown_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }


        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.profissional,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner3.adapter = adapter
        }


        val mPickTimeBtn = binding.pickDateBtn
        val textView     = binding.dateTv

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        mPickTimeBtn.setOnClickListener {

            val dpd = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView
                textView.setText("" + dayOfMonth + " " + month + ", " + year)
            }, year, month, day)
            dpd.show()

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}