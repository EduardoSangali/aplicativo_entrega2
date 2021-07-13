package com.example.appteste1.ui.paciente

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.example.appteste1.R
import com.example.appteste1.databinding.FragmentFragmentoPacienteBinding
import com.example.appteste1.ui.dashboard.DashboardFragment

import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentoPaciente.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentoPaciente : Fragment() {

    private lateinit var fragmentoPacienteViewModel: FragmentoPacienteViewModel
    private lateinit var repository: PacienteRepository
    private var _binding: FragmentFragmentoPacienteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentoPacienteViewModel = ViewModelProvider(this).get(FragmentoPacienteViewModel::class.java)
        _binding = FragmentFragmentoPacienteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        repository = PacienteRepository()
        repository.initializeDbRef()

        val cancelBtn = binding.deletar
        val salvarBtn = binding.enviar

        val nome: EditText = binding.nome
        val idade: EditText = binding.idade
        val telefone: EditText = binding.telefone
        val email: EditText = binding.email

        cancelBtn.setOnClickListener(){
            nome.setText("")
            idade.setText("")
            telefone.setText("")
            email.setText("")
        }

        salvarBtn.setOnClickListener(){
            var paciente = Paciente(nome.getText().toString(),
                idade.getText().toString(),
                telefone.getText().toString(),
                email.getText().toString(),
                "54265879541")

            repository.insert(paciente)
        }
        binding.backIcon.setOnClickListener(View.OnClickListener {
            replaceView(DashboardFragment())
        })


        return root

    }
    private fun replaceView(fragment: Fragment) {

        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_host_fragment_activity_main,fragment)
        fragmentTransaction.commit()

    }




}