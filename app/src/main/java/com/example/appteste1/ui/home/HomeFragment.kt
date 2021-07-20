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
import com.example.appteste1.ui.notifications.NotificationsFragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

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
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "645644645",
                "26/04/21 - 10:15", "Psicólogo",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "645644645",
                "12/05/21 - 11:45", "Personal Trainer",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "645644645",
                "25/05/21 - 08:15", "Fisioterapia",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "645644645",
                "01/06/21 - 16:15", "Pilates",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "645644645",
                "10/06/21 - 12:45", "Fisioterapia",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "645644645",
                "29/07/21 - 08:30", "Psicólogo",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "645644645",
                "30/07/21 - 11:15", "Personal Trainer",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "645644645",
                "23/04/21 - 14:00", "Fisioterapia",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "645644645",
                "26/04/21 - 10:15", "Psicólogo",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "645644645",
                "12/05/21 - 11:45", "Personal Trainer",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "645644645",
                "25/05/21 - 08:15", "Fisioterapia",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "645644645",
                "01/06/21 - 16:15", "Pilates",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "645644645",
                "10/06/21 - 12:45", "Fisioterapia",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "645644645",
                "29/07/21 - 08:30", "Psicólogo",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "645644645",
                "30/07/21 - 11:15", "Personal Trainer",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "645644645",
                "23/04/21 - 14:00", "Fisioterapia",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "645644645",
                "26/04/21 - 10:15", "Psicólogo",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "645644645",
                "12/05/21 - 11:45", "Personal Trainer",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "645644645",
                "25/05/21 - 08:15", "Fisioterapia",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "645644645",
                "01/06/21 - 16:15", "Pilates",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "645644645",
                "10/06/21 - 12:45", "Fisioterapia",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )


        val listView: ListView = binding.listview
        val adapter = MyAdapter(requireContext(), R.layout.list_item, Items, true, true, true)
        listView.adapter = adapter

         */


       /* listView.setOnItemClickListener { listView, view, i, l ->
            Toast.makeText(requireContext(), "Você clicou em ${Items[i].procedim}", Toast.LENGTH_SHORT).show()
        }*/
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