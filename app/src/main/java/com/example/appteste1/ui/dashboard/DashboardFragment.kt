package com.example.appteste1.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.appteste1.R
import com.example.appteste1.databinding.FragmentDashboardBinding


import android.widget.ListView
import com.example.appteste1.model.bean.Agendamento
import com.example.appteste1.ui.home.MyAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var database: DatabaseReference

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val allAppointments = ArrayList<Agendamento>()

        // get a reference of the database object
        database = Firebase.database.reference
        database.child("agendamentos").get().addOnSuccessListener{
            Log.i("firebase", "Value: " + it.value)

            //create a generic type to be used to extract the data from Firebase
            val genericTypeIndicator: GenericTypeIndicator<Map<String, Agendamento>> =
                object : GenericTypeIndicator<Map<String, Agendamento>>() {}

            //The getValue method will convert the JSON string to a hashmap
            val hashMap: Map<String, Agendamento>? = it.getValue(genericTypeIndicator)
            if (hashMap != null) {
                //loop over the map items and add
                for ((_, agendamento) in hashMap) {
                    allAppointments.add(agendamento)
                }
            }

            val listView: ListView = binding.listview
            //val adapter = MyAdapter(requireContext(), R.layout.list_item, Items, true, false, false)
            val adapter = MyAdapter(requireContext(), R.layout.list_item, allAppointments, true, false, false)
            listView.adapter = adapter
        }

        /*val Items = ArrayList<Procedimento_>()

        Items.add(
            Procedimento_(
                "645644645",
                "23/04/21 - 14:00", "Fisioterapia",
                R.drawable.list_info, null, null
            )
        )
        Items.add(
            Procedimento_(
                "645644645",
                "26/04/21 - 10:15", "Psicólogo",
                R.drawable.list_info, null, null
            )
        )


        val listView: ListView = binding.listview
        val adapter = MyAdapter(requireContext(), R.layout.list_item, Items, true, false, false)
        listView.adapter = adapter*/

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
}