package com.example.appteste1.ui.contacts

import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.appteste1.R


class ContactsFragment : DialogFragment() {
    override fun onCreateView(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: View = layoutInflater.inflate(
            R.layout.fragment_contacts,
            container,
            false
        )

        //Get items
        val closeContactDialoo: ImageView = rootView.findViewById<ImageView>(R.id.closeContact)
        val whatsApp_Contact: ImageView = rootView.findViewById<ImageView>(R.id.imgWatsContact)
        val telephone_Contact: ImageView = rootView.findViewById<ImageView>(R.id.imgPhoneContact)
        val mail_Contact: ImageView = rootView.findViewById<ImageView>(R.id.imgMailContact)


        closeContactDialoo.setOnClickListener(){
            dismiss()
        }
        whatsApp_Contact.setOnClickListener(){
            //Insert action here
        }
        telephone_Contact.setOnClickListener(){
            //Insert action here
        }
         mail_Contact.setOnClickListener(){
             //Insert action here
        }

        return rootView
    }
}


