package com.apollogit.shido.whatsappclone

import android.os.Bundle
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

        auth.signInWithEmailAndPassword("goku@super.com", "goku22")
                .addOnCompleteListener { task: Task<AuthResult> ->
                    if(task.isSuccessful){
                        //Sign in was successful
                        Log.e("Success", "Success")
                        Toast.makeText(this@MainActivity, "Signed In Successful", Toast.LENGTH_LONG).show()
                        currentUser = task.result.user
                    }else{
                        Log.e("Error", task.exception?.localizedMessage)
                        Toast.makeText(this@MainActivity, "Signed In Error", Toast.LENGTH_LONG).show()
                    }
                }

      /*  // Write a message to the database
        val database = FirebaseDatabase.getInstance()
        val dbReference = database.getReference("messages").push()

        dbReference.setValue(Employee(
                "Jam2es Bond",
                "Spy",
                10,
                "England")
        )

        //Read from DB
        *//*     dbReference.onDataChange { dataSnapshot ->
                 val value = dataSnapshot?.value as HashMap<String, Any>
                 hello_world.text = value["name"].toString()


             }*//*

        hello_world.setOnClickListener {
            val employee = Employee(
                    "James Bond",
                    "Spy",
                    10,
                    "England")

            dbReference.setValue(employee)

        }*/
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
