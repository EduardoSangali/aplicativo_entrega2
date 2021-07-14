package com.example.appteste1.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.appteste1.R
import com.example.appteste1.databinding.FragmentDashboardBinding


import android.widget.ListView
import com.example.appteste1.ui.home.HomeFragment
import com.example.appteste1.ui.home.MyAdapter
import com.example.appteste1.ui.home.Procedimento_
import com.example.appteste1.ui.paciente.FragmentoPaciente

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
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



        val Items = ArrayList<Procedimento_>()

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
        listView.adapter = adapter

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