package com.example.appteste1.ui.paciente

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.example.appteste1.R
import com.example.appteste1.databinding.FragmentFragmentoPacienteBinding
import com.example.appteste1.model.paciente.Paciente
import com.example.appteste1.ui.dashboard.DashboardFragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

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
    private lateinit var database: DatabaseReference
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

        initializeDbRef()

        val cancelBtn = binding.deletar
        val salvarBtn = binding.enviar

        val nome: EditText = binding.nome
        val idade: EditText = binding.idade
        val telefone: EditText = binding.telefone
        val email: EditText = binding.email
        val documento: EditText = binding.personalID

        cancelBtn.setOnClickListener(){
            nome.setText("")
            idade.setText("")
            telefone.setText("")
            email.setText("")
            documento.setText("")
        }

        salvarBtn.setOnClickListener(){
            var paciente = Paciente(nome.getText().toString(),
                idade.getText().toString(),
                telefone.getText().toString(),
                email.getText().toString(),
                documento.getText().toString())

           insertPaciente(paciente)
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

    fun showAlert(title: String, message: String, buttonText: String = "OK") {
        val builder =
            AlertDialog.Builder(requireContext())
        builder.setTitle(title)
        builder.setMessage(message)
            .setCancelable(false)
            .setPositiveButton(buttonText) { dialog, id ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }

    fun initializeDbRef() {
        // [START initialize_database_ref]
        Log.i("firebase", "Init Firebase Database")
        database = Firebase.database.reference
        // [END initialize_database_ref]
        Log.i("firebase", "Init Firebase Database DONE")
    }

    fun insertPaciente(paciente: Paciente){
        database.child("pacientes").child(paciente.documento).setValue(paciente)
            .addOnSuccessListener {
                Log.i("firebase","Successfully inserted.")
                showAlert("Incluir Paciente", "Paciente incluído com sucesso.")
            }
            .addOnFailureListener {
                Log.e("firebase", "Error writing data", it)
                showAlert("Incluir Paciente", "Não foi possível incluir paciente.")
            }
    }
}