package com.example.appteste1.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.appteste1.model.bean.Agendamento
import com.example.appteste1.model.repository.AppointmentRepository
import javax.inject.Inject

class HistoryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    appointmentRepository: AppointmentRepository
) : ViewModel() {
    val patientId : String = savedStateHandle["patientId"] ?:
        throw IllegalArgumentException("missing patient id")

    private val _appointment = MutableLiveData<Agendamento>()
    val appointment : LiveData<Agendamento> = _appointment

    private val _appointmentsList = MutableLiveData<MutableList<Agendamento>>()

    init {
        _appointmentsList.value = ArrayList()
        appointmentRepository.getAllAppointments()
    }

    fun addAppointment(appointment: Agendamento) {
        _appointmentsList.value?.add(appointment)
        _appointmentsList.value = _appointmentsList.value
    }
}