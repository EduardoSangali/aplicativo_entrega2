package com.example.appteste1.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.appteste1.R
import com.example.appteste1.databinding.FragmentHomeBinding
import android.widget.ListView
import com.example.appteste1.MyAdapter
import android.widget.Toast



class HomeFragment : Fragment() {

    private lateinit var myAdapter: MyAdapter

    private lateinit var homeViewModel: HomeViewModel
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

        val Items = ArrayList<Procedimento_>()
        Items.add(
            Procedimento_(
                "23/04/21 - 14:00", "Fisioterapia",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "26/04/21 - 10:15", "Psicólogo",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "12/05/21 - 11:45", "Personal Trainer",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "25/05/21 - 08:15", "Fisioterapia",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "01/06/21 - 16:15", "Pilates",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "10/06/21 - 12:45", "Fisioterapia",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "29/07/21 - 08:30", "Psicólogo",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "30/07/21 - 11:15", "Personal Trainer",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "23/04/21 - 14:00", "Fisioterapia",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "26/04/21 - 10:15", "Psicólogo",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "12/05/21 - 11:45", "Personal Trainer",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "25/05/21 - 08:15", "Fisioterapia",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "01/06/21 - 16:15", "Pilates",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "10/06/21 - 12:45", "Fisioterapia",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "29/07/21 - 08:30", "Psicólogo",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "30/07/21 - 11:15", "Personal Trainer",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "23/04/21 - 14:00", "Fisioterapia",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "26/04/21 - 10:15", "Psicólogo",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "12/05/21 - 11:45", "Personal Trainer",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "25/05/21 - 08:15", "Fisioterapia",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "01/06/21 - 16:15", "Pilates",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "10/06/21 - 12:45", "Fisioterapia",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "29/07/21 - 08:30", "Psicólogo",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "30/07/21 - 11:15", "Personal Trainer",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "23/04/21 - 14:00", "Fisioterapia",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "26/04/21 - 10:15", "Psicólogo",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "12/05/21 - 11:45", "Personal Trainer",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "25/05/21 - 08:15", "Fisioterapia",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "01/06/21 - 16:15", "Pilates",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "10/06/21 - 12:45", "Fisioterapia",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "29/07/21 - 08:30", "Psicólogo",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )
        Items.add(
            Procedimento_(
                "30/07/21 - 11:15", "Personal Trainer",
                R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete
            )
        )

        val listView: ListView = binding.listview
        val adapter = MyAdapter(requireContext(), R.layout.list_item, Items)
        listView.adapter = adapter

        listView.setOnItemClickListener { listView, view, i, l ->
            Toast.makeText(requireContext(), "Você clicou em ${Items[i].procedim}", Toast.LENGTH_SHORT).show()
        }



        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}