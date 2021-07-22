package com.example.appteste1.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.appteste1.R
import com.example.appteste1.databinding.FragmentHomeBinding
import android.widget.ListView

import android.util.Log
import com.example.appteste1.model.bean.Agendamento
import com.example.appteste1.model.repository.AppointmentRepository
import com.example.appteste1.ui.notifications.NotificationsFragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    private lateinit var myAdapter: MyAdapter

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var database: DatabaseReference

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val allAppointments = ArrayList<Agendamento>()

        val currentDate = Date().time.toString()

        // get a reference of the database object
        database = Firebase.database.reference
        database.child("agendamentos")
            .orderByChild("dataHoraTS").startAt(currentDate).get().addOnSuccessListener{
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
            val adapter = MyAdapter(requireContext(), R.layout.list_item, allAppointments, true, true, true)
            listView.adapter = adapter
        }

        binding.floatingActionButton.setOnClickListener(View.OnClickListener {
             replaceTest(NotificationsFragment())
        })



        return root

    }
    private fun replaceTest(fragment: Fragment) {

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