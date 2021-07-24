package com.example.appteste1.ui.appointment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.appteste1.R
import com.example.appteste1.model.bean.Agendamento
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AppointmentFragment : Fragment() {

    private lateinit var adapter: AppointmentListAdapter
    private lateinit var viewModel: AppointmentViewModel

    /* *
     * This method creates the view
     * We will just inflate the layout to have the main view loaded
     */
    override fun onCreateView(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("APPOINTMENT FRAGMENT", "onCreateView -> START")
        return layoutInflater.inflate(R.layout.fragment_appointment, container, false);
    }

    /**
     * After the view is created we invoke this method to populate the data
     * and start the observer
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        Log.i("APPOINTMENT FRAGMENT", "VIEW CREATED. POPULATING DATA...")
        viewModel =
            ViewModelProvider(this).get(AppointmentViewModel::class.java)

        // Create the adapter
        adapter = AppointmentListAdapter(requireContext(), R.layout.list_item, ArrayList<Agendamento>() ,true, true, true, viewModel)

        // Get the list view and associate the adapter with it
        val listView = view.findViewById<ListView>(R.id.listview)
        listView.adapter = adapter

        // Create an observer for the appointments list
        val observer = Observer<ArrayList<Agendamento>> { newList ->
            Log.i("OBSERVER", "List updated")
            adapter.clear()
            adapter.addAll(newList)
            adapter.notifyDataSetChanged()
        }

        // Associate the observer with the appointments list present in the view model
        viewModel.appointmentList.observe(viewLifecycleOwner, observer)
        viewModel.loadAppointmentsList(requireContext())

        // Get the floating button and add a click listener to it
        val floatingActionButton : FloatingActionButton = view.findViewById(R.id.floatingActionButton)
        floatingActionButton.setOnClickListener(View.OnClickListener {
            Navigation.findNavController(it).navigate(R.id.navigation_save_appointment)
        })

        Log.i("APPOINTMENT FRAGMENT", "VIEW CREATED. DATA POPULATED.")
    }
}
