package com.tugasakhir.shotbook.auth.halamanRegister.presenter

import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.tugasakhir.shotbook.auth.halamanRegister.view.RegisterActivity


class RegisterPresenter(
    val listener: Listener,
    val mAuth: FirebaseAuth,
    val activity: RegisterActivity
) {
    interface Listener {
        fun toNameFragment()
        fun toEmailFragment()
        fun toPasswordFragment()
    }

    lateinit var accountRole: String
    lateinit var firstName: String
    lateinit var lastName: String
    lateinit var email: String
    lateinit var password: String

    fun setRoleAccount(role: String) {
        accountRole = role
    }

    fun setNameAccount(fName: String, lName: String) {
        firstName = fName
        lastName = lName
    }

    fun setEmailAccount(mail: String) {
        email = mail
    }

    fun setPasswordAccount(pw: String) {
        password = pw
    }

    fun showLog() {
        Log.d("Hasil", "$accountRole, $firstName $lastName, $email, $password")
    }

    fun register() {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity,
                OnCompleteListener<AuthResult?> { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("register", "createUserWithEmail:success")
                        val user: FirebaseUser? = mAuth.getCurrentUser()
                        user?.let { saveData(it) }
                        //updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("register", "createUserWithEmail:failure", task.exception)
//                        Toast.makeText(
//                            this@EmailPasswordActivity, "Authentication failed.",
//                            Toast.LENGTH_SHORT
//                        ).show()
                        //updateUI(null)
                    }

                    // ...
                })
    }

    fun saveData(usr: FirebaseUser) {
        //get user data

        val db = FirebaseFirestore.getInstance()

        // Create a new user with a first and last name
        // Create a new user with a first and last name
        val user: MutableMap<String, Any> = HashMap()
        user["first_name"] = firstName
        user["last_name"] = lastName
        user["role"] = accountRole

// Add a new document with a generated ID

// Add a new document with a generated ID
        db.collection("users")
            .document(usr.uid)
            .set(user)
            .addOnSuccessListener {
                Log.d(
                    "register db",
                    "account registered"
                )
            }
            .addOnFailureListener { e -> Log.w("register db", "Error adding document", e) }
    }

}