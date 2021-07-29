package com.example.appteste1.ui.contacts

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
            val url = "https://api.whatsapp.com/send?phone=+551112345678"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
        telephone_Contact.setOnClickListener(){
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode("+551112345678")))
            startActivity(intent)
        }
        mail_Contact.setOnClickListener(){
            val recipient = "contato.clintech@clintech.com.br"
            val subject = "Mensagem vinda do aplicativo Clintech"
            val message = "Olá! Gostaria de entrar em contato com vocês!"

            sendEmail(recipient, subject, message)
        }

        return rootView
    }
    private fun sendEmail(recipient: String, subject: String, message: String) {
        /*ACTION_SEND action to launch an email client installed on your Android device.*/
        val mIntent = Intent(Intent.ACTION_SEND)
        /*To send an email you need to specify mailto: as URI using setData() method
        and data type will be to text/plain using setType() method*/
        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"
        // put recipient email in intent
        /* recipient is put as array because you may wanna send email to multiple emails
           so enter comma(,) separated emails, it will be stored in array*/
        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
        //put the Subject in the intent
        mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        //put the message in the intent
        mIntent.putExtra(Intent.EXTRA_TEXT, message)


        try {
            //start email intent
            startActivity(Intent.createChooser(mIntent, "Choose Email Client..."))
        }
        catch (e: Exception){
            //if any thing goes wrong for example no email client application or any exception
            //get and show exception message
            Toast.makeText(requireContext(), e.message, Toast.LENGTH_LONG).show()
        }

    }
}


