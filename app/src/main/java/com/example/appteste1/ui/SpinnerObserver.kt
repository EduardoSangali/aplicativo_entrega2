package com.example.appteste1.ui

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.Observer
import com.example.appteste1.R


class SpinnerObserver(val ctx : Context, val spinner: Spinner) : Observer<MutableList<String>> {

    override fun onChanged(it: MutableList<String>) {
        val arrayAdapter = ArrayAdapter(ctx,
            R.layout.color_spinner_layout,
            it)
            .also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(R.layout.color_spinner_dropdown_layout)
                // Apply the adapter to the spinner
                spinner.adapter = adapter
            }
    }

}