package com.example.appteste1.ui.appointment

import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.appteste1.R
import com.example.appteste1.model.bean.Agendamento

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before


@RunWith(AndroidJUnit4::class)
class AppointmentAdapterTest {
    lateinit var mAdapter : AppointmentListAdapter

    lateinit var viewModel: AppointmentViewModel
    lateinit var viewGroup: ViewGroup

    private var agendamento1: Agendamento = Agendamento("","","","","","")
    private var agendamento2: Agendamento = Agendamento("","","","","","")

    @Before
    fun setUp() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        viewModel = AppointmentViewModel()
        viewGroup = ListView(appContext)

        val data: ArrayList<Agendamento> = ArrayList<Agendamento>()
        agendamento1 = Agendamento("1254685","28/07/2021 10:30", "Fisioterapia", "teste","Dr. João Miguel Saran", "12546325")
        agendamento2 = Agendamento("9853124","25/08/2021 11:30", "Acupuntura", "teste","Dr. João Miguel Saran", "12546953")
        data.add(agendamento1)
        data.add(agendamento2)
        mAdapter = AppointmentListAdapter(appContext,
            R.layout.list_item, data, false, false, false, viewModel)
    }

    @Test
    fun testGetItem() {
        assertEquals(
            "Fisioterapia was expected.", agendamento1.procedim,
            (mAdapter?.getItem(0) as Agendamento).procedim
        )
    }

    @Test
    fun testGetItemId() {
        assertEquals("Wrong ID.", 0, mAdapter?.getItemId(0))
    }

    @Test
    fun testGetView() {
        val view: View = mAdapter.getView(0, null, viewGroup)

        val dataProced: TextView = view.findViewById(R.id.listaDataHora)
        val nomeProced: TextView = view.findViewById(R.id.listaEspecialidade2)

        //On this part you will have to test it with your own views/data
        assertNotNull("View is null. ", view)
        assertNotNull("Procedure Date TextView is null. ", dataProced)
        assertNotNull("Procedure Name TextView is null. ", nomeProced)
        assertEquals("Procedure Names doesn't match.",
            agendamento1.procedim, nomeProced.text)
        assertEquals("Procedure Date doesn't match.",
            agendamento1.dataHora, dataProced.text
        )
    }
}