package com.example.appteste1

import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.appteste1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            this.supportActionBar?.hide()
        } catch (e: NullPointerException) {
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

/*
        //LISTA DE ITENS DA HOME
        val Items = ArrayList<Procedimento>()
        Items.add(Procedimento("23/04/21 - 14:00", "Fisioterapia",
            R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete ))
        Items.add(Procedimento("26/04/21 - 10:15", "Psicólogo",
            R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete ))
        Items.add(Procedimento("12/05/21 - 11:45", "Personal Trainer",
            R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete ))
        Items.add(Procedimento("25/05/21 - 08:15", "Fisioterapia",
            R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete ))
        Items.add(Procedimento("01/06/21 - 16:15", "Pilates",
            R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete ))
        Items.add(Procedimento("10/06/21 - 12:45", "Fisioterapia",
            R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete ))
        Items.add(Procedimento("29/07/21 - 08:30", "Psicólogo",
            R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete ))
        Items.add(Procedimento("30/07/21 - 11:15", "Personal Trainer",
            R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete ))
        Items.add(Procedimento("23/04/21 - 14:00", "Fisioterapia",
            R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete ))
        Items.add(Procedimento("26/04/21 - 10:15", "Psicólogo",
            R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete ))
        Items.add(Procedimento("12/05/21 - 11:45", "Personal Trainer",
            R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete ))
        Items.add(Procedimento("25/05/21 - 08:15", "Fisioterapia",
            R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete ))
        Items.add(Procedimento("01/06/21 - 16:15", "Pilates",
            R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete ))
        Items.add(Procedimento("10/06/21 - 12:45", "Fisioterapia",
            R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete ))
        Items.add(Procedimento("29/07/21 - 08:30", "Psicólogo",
            R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete ))
        Items.add(Procedimento("30/07/21 - 11:15", "Personal Trainer",
            R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete ))
        Items.add(Procedimento("23/04/21 - 14:00", "Fisioterapia",
            R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete ))
        Items.add(Procedimento("26/04/21 - 10:15", "Psicólogo",
            R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete ))
        Items.add(Procedimento("12/05/21 - 11:45", "Personal Trainer",
            R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete ))
        Items.add(Procedimento("25/05/21 - 08:15", "Fisioterapia",
            R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete ))
        Items.add(Procedimento("01/06/21 - 16:15", "Pilates",
            R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete ))
        Items.add(Procedimento("10/06/21 - 12:45", "Fisioterapia",
            R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete ))
        Items.add(Procedimento("29/07/21 - 08:30", "Psicólogo",
            R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete ))
        Items.add(Procedimento("30/07/21 - 11:15", "Personal Trainer",
            R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete ))
        Items.add(Procedimento("23/04/21 - 14:00", "Fisioterapia",
            R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete ))
        Items.add(Procedimento("26/04/21 - 10:15", "Psicólogo",
            R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete ))
        Items.add(Procedimento("12/05/21 - 11:45", "Personal Trainer",
            R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete ))
        Items.add(Procedimento("25/05/21 - 08:15", "Fisioterapia",
            R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete ))
        Items.add(Procedimento("01/06/21 - 16:15", "Pilates",
            R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete ))
        Items.add(Procedimento("10/06/21 - 12:45", "Fisioterapia",
            R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete ))
        Items.add(Procedimento("29/07/21 - 08:30", "Psicólogo",
            R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete ))
        Items.add(Procedimento("30/07/21 - 11:15", "Personal Trainer",
            R.drawable.list_info, R.drawable.list_edit, R.drawable.list_delete ))


        //Create listview and array adapter
        val listView = findViewById<ListView>(R.id.listview)
        intent
        val adapter = MyAdapter(this,R.layout.list_item,Items)
        listView.adapter = adapter

        listView.setOnItemClickListener { adapter, view, i, l ->
            Toast.makeText(this, "Você clicou em ${Items[i].procedim}", Toast.LENGTH_SHORT).show()
        }
*/
    }
}