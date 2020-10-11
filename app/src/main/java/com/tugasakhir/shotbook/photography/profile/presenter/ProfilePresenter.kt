package com.tugasakhir.shotbook.photography.profile.presenter

import android.net.Uri
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage

class ProfilePresenter(val listener: Listener) {
    interface Listener {
        fun showData(data: Map<String, Any>)
    }

    private var dataProfil: Map<String, Any>? = null
    private val db = FirebaseFirestore.getInstance().collection("users")

    fun getAllData(id: String) {
        val database = db.document(id)

        database.addSnapshotListener { snapshot, e ->
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

    fun uploadImage(filePath: Uri?, id: String){
        if(filePath != null){
            val firebaseStorageRef = FirebaseStorage.getInstance().reference
            val ref = firebaseStorageRef.child("profile_picture/user-$id")

            ref.putFile(filePath).continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                ref.downloadUrl
            }.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result

                    savePhotoProfile(downloadUri.toString(), id)
                }
            }

//            ref.putFile(filePath).addOnCompleteListener {
//
//            }.addOnSuccessListener {
//                //                Toast.makeText(activity, "Image Uploaded", Toast.LENGTH_SHORT).show()
//                val uriDownload = it.task.result
//
//                Log.d("URI Photo Profile", "$uriDownload")
////                savePhotoProfile()
//            }.addOnFailureListener {
//        //                Toast.makeText(activity, "Image Uploading Failed " + e.message, Toast.LENGTH_SHORT).show()
//            }
        }
    }

    private fun savePhotoProfile(uri: String, id: String){
        val data = hashMapOf("profile_picture" to uri)

        db.document(id)
            .set(data, SetOptions.merge())
            .addOnSuccessListener {
                Log.d(
                    "Set Photo Profile",
                    "Profile Picture setted"
                )
            }
            .addOnFailureListener { e -> Log.w("Set Photo Profile", "Error adding document", e) }
    }
}