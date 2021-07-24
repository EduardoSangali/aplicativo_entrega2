package com.example.appteste1

import com.example.appteste1.model.bean.Agendamento
import com.example.appteste1.model.repository.AppointmentRepository
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.modules.junit4.PowerMockRunnerDelegate
import java.util.*


/**
 *
 *
 *
 */
@RunWith(PowerMockRunner::class)
@PowerMockRunnerDelegate(JUnit4::class)
@PrepareForTest(FirebaseDatabase::class)
class AppointmentRepositoryUnitTest {
    private val repository = AppointmentRepository()

    private var mockedDatabaseReference: DatabaseReference? = null

    @Before
    fun setup(){
        mockedDatabaseReference = Mockito.mock(DatabaseReference::class.java)

        val mockedFirebaseDatabase: FirebaseDatabase = Mockito.mock(FirebaseDatabase::class.java)
        PowerMockito.`when`(mockedFirebaseDatabase.reference).thenReturn(mockedDatabaseReference)

        PowerMockito.mockStatic(FirebaseDatabase::class.java)
        PowerMockito.`when`(FirebaseDatabase.getInstance()).thenReturn(mockedFirebaseDatabase)
    }

    @Test
    fun testInsertAppointment() {
        val c = Calendar.getInstance()

        var appointment = Agendamento(c.timeInMillis.toString(), "22/08/2021 15:30", "Fisioterapia", "teste@teste.com", "Dr Marcos Bellucci")

        repository.insertAppointment(appointment)
    }
}