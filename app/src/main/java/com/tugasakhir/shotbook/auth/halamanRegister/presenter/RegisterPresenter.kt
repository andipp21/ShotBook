package com.tugasakhir.shotbook.auth.halamanRegister.presenter

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.tugasakhir.shotbook.auth.halamanRegister.view.RegisterActivity


class RegisterPresenter(
    val listener: Listener,
    private val mAuth: FirebaseAuth,
    val activity: RegisterActivity
) {
    interface Listener {
        fun toNameFragment()
        fun toPasswordFragment()
    }

    private lateinit var accountRole: String
    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var email: String
    private lateinit var city: String
    private lateinit var phoneNumber: String
    private lateinit var password: String

    fun setRoleAccount(role: String) {
        accountRole = role
    }

    fun setPersonalData(fName: String, lName: String, mail: String, ct: String, pn: String) {
        firstName = fName
        lastName = lName
        email = mail
        city = ct
        phoneNumber = pn
    }

    fun setPasswordAccount(pw: String) {
        password = pw
    }

    fun showLog() {
        Log.d("Hasil", "$accountRole, $firstName $lastName, $email, $password")
    }

    fun register() {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                activity
            ) { task ->
                if (task.isSuccessful) {
                    Log.d("register", "createUserWithEmail:success")
                    val user: FirebaseUser? = mAuth.currentUser
                    user?.let { saveData(it) }
                } else {
                    Log.w("register", "createUserWithEmail:failure", task.exception)
                }
            }
    }

    private fun saveData(usr: FirebaseUser) {
        val db = FirebaseFirestore.getInstance().collection("users")

        // Create a new user with a first and last name
        val user: MutableMap<String, Any> = HashMap()
        user["first_name"] = firstName
        user["last_name"] = lastName
        user["email"] = email
        user["city"] = city
        user["phone_number"] = phoneNumber
        user["role"] = accountRole

// Add a new document with a generated ID
        db.document(usr.uid)
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