package com.example.appteste1.ui.login

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.auth.FirebaseAuth

class LogoutListener : View.OnClickListener {
    private lateinit var auth: FirebaseAuth

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    override fun onClick(v: View?) {
        val builder = AlertDialog.Builder(v?.context)
        builder.setTitle("Sair")
        builder.setMessage("Você realmente deseja sair do aplicativo?")
            .setCancelable(true)
            .setPositiveButton("OK") { dialog, id ->
                dialog.dismiss()
                logout();
                v?.context?.startActivity(Intent(v.context, LoginActivity::class.java))
            }
            .setNegativeButton("Cancel") { dialog, id ->
                dialog.cancel()
            }
        val alert = builder.create()
        alert.show()
    }

    fun logout() {
        auth = FirebaseAuth.getInstance()
        auth.signOut();
    }
}