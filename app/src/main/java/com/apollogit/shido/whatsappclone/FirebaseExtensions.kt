package com.apollogit.shido.whatsappclone

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

/**
 * Created by Shido on 11/03/2018.
 */
fun DatabaseReference.onDataChange(action: (dataSnapshot: DataSnapshot?) -> Unit){
    this.addValueEventListener(object : ValueEventListener{
        override fun onCancelled(p0: DatabaseError?) {}

        override fun onDataChange(dataSnapshot: DataSnapshot?) {
           action(dataSnapshot)
        }

    })
}