package com.tugasakhir.shotbook.photography.profile.presenter

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProfilePresenter(val listener: Listener) {
    interface Listener {
        fun showData(data: Map<String, Any>)
    }

    var dataProfil: Map<String, Any>? = null

    fun getAllData(id: String) {
        val db = FirebaseFirestore.getInstance().collection("users").document(id)

        db.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w("Get Data Snapshot", "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                Log.d("Get Data Snapshot", "Current data: ${snapshot.data}")

                val data = snapshot.data
                data?.let {
                    listener.showData(it)
                    dataProfil = it
                }
            } else {
                Log.d("Get Data Snapshot", "Current data: null")
            }
        }
    }
}