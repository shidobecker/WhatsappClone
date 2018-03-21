package com.apollogit.shido.whatsappclone

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    var currentUser: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        setupLoginButton()

    }

    private fun setupLoginButton() {
        loginButton.setOnClickListener {

            if(email.text.isEmpty() || password.text.isEmpty())
                return@setOnClickListener

            val email = email.text.toString().trim()
            val password = password.text.toString().trim()

            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, { task: Task<AuthResult> ->
                        if (task.isSuccessful) {
                            val user: FirebaseUser = checkNotNull(auth.currentUser)
                            Log.d("User:", user.email)
                        } else {
                            Snackbar.make(rootLayout, task.exception.toString(), Snackbar.LENGTH_SHORT)
                        }
                    })
        }
    }


    override fun onStart() {
        super.onStart()

        currentUser = auth.currentUser
        if (currentUser != null) {
            Toast.makeText(this, "User is ${currentUser?.email}", Toast.LENGTH_SHORT).show()
        } else {
            // Toast.makeText(this, "User Logged Out", Toast.LENGTH_SHORT).show()
        }
        //Call a function to update the userInterface with current user
    }


}
