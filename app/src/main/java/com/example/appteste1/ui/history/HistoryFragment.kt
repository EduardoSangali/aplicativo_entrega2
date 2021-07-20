package com.example.appteste1.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import com.example.appteste1.R
import com.example.appteste1.ui.notifications.NotificationsViewModel
import kotlin.reflect.KProperty

class HistoryFragment: Fragment(){
    private val viewModel: HistoryViewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.appointment.observe(viewLifecycleOwner) {
            // update UI
        }
    }
}

