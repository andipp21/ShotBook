package com.tugasakhir.shotbook.photography.profile.presenter

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions


class ProfileAboutPresenter(val listener: Listener) {
    interface Listener {
        fun showData(data: Map<String, Any>)
    }

    private val db = FirebaseFirestore.getInstance().collection("users")

    fun setData(dt: Map<String, Any>) {

        listener.showData(dt)
    }

    fun save(dt: Map<String, Any>, id: String) {
        val document = id.let { db.document(it) }
        document.set(dt, SetOptions.merge())
            .addOnSuccessListener {
                Log.d(
                    "Set Data Profile",
                    "Data setted"
                )
            }
            .addOnFailureListener { e -> Log.w("Set Data Profile", "Error adding document", e) }
    }
}