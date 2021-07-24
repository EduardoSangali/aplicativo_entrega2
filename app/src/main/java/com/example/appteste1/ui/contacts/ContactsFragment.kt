package com.example.appteste1.ui.contacts

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment


class ContactsFragment : DialogFragment() {
    fun ContactsFragment() {
        // Empty constructor required for DialogFragment
    }

    fun newInstance(title: String?) {
        val frag = ContactsFragment()
        val args = Bundle()
        args.putString("title", title)
        arguments = args
        return frag
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val title = requireArguments().getString("title")
        val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(activity)
        alertDialogBuilder.setTitle(title)
        alertDialogBuilder.setMessage("Are you sure?")
        alertDialogBuilder.setPositiveButton("OK", { dialog, which ->
                // on success
            })
        alertDialogBuilder.setNegativeButton("Cancel", { dialog, which ->
                if (dialog != null) {
                    dialog.dismiss()
                }
            })
        return alertDialogBuilder.create()
    }
}
