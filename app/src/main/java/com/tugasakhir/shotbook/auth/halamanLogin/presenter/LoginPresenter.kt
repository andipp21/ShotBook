package com.tugasakhir.shotbook.auth.halamanLogin.presenter

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.tugasakhir.shotbook.auth.halamanLogin.view.LoginActivity


class LoginPresenter(
    private val listener: Listener,
    private val mAuth: FirebaseAuth,
    private val activity: LoginActivity
) {
    interface Listener {
        fun buttonEnabled()
        fun buttonDisabled()
    }

    private lateinit var emailLogin: String
    private lateinit var passwordLogin: String

    private var stateEmail = false
    private var statePassword = false

    fun setEmail(email: String) {
        if (email == "") {
            stateEmail = false
        } else {
            stateEmail = true
            emailLogin = email
        }

        stateChecker()
    }

    fun setPassword(pw: String) {
        if (pw == "") {
            statePassword = false
        } else {
            statePassword = true
            passwordLogin = pw
        }

        stateChecker()
    }

    fun stateChecker() {
        if (stateEmail && statePassword) {
            listener.buttonEnabled()
        } else {
            listener.buttonDisabled()
        }
    }

    fun login() {
        mAuth.signInWithEmailAndPassword(emailLogin, passwordLogin)
            .addOnCompleteListener(
                activity
            ) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Sign-In", "signInWithEmail:success")
                    val user = mAuth.currentUser
                    user?.let { getData(it) }
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Sign-In", "signInWithEmail:failure", task.exception)
//                        Toast.makeText(
//                            this@EmailPasswordActivity, "Authentication failed.",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                        updateUI(null)
                }

                // ...
            }
    }

    fun getData(usr: FirebaseUser){
        val db = FirebaseFirestore.getInstance()

        val docRef = db.collection("users").document(usr.uid)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d("Data Login", "DocumentSnapshot data: ${document.data}")
                } else {
                    Log.d("Data Login", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("Data Login", "get failed with ", exception)
            }
    }
}