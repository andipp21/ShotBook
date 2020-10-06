package com.tugasakhir.shotbook.auth.halamanRegister.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.auth.FirebaseAuth
import com.tugasakhir.shotbook.R
import com.tugasakhir.shotbook.auth.halamanRegister.presenter.RegisterPresenter
import com.tugasakhir.shotbook.auth.halamanRegister.view.fragment.RegisterAddEmailFragment
import com.tugasakhir.shotbook.auth.halamanRegister.view.fragment.RegisterAddNameFragment
import com.tugasakhir.shotbook.auth.halamanRegister.view.fragment.RegisterAddPasswordFragment
import com.tugasakhir.shotbook.auth.halamanRegister.view.fragment.RegisterSelectRoleFragment
import com.tugasakhir.shotbook.databinding.ActivityRegisterBinding


class RegisterActivity : AppCompatActivity(), RegisterPresenter.Listener {
    private lateinit var binding: ActivityRegisterBinding
    lateinit var presenter: RegisterPresenter
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        mAuth = FirebaseAuth.getInstance()
        presenter = RegisterPresenter(this, mAuth, this)

        setContentView(binding.root)

        supportActionBar?.title = "Kalkulator"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //set default fragment
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.flContainerRegister, RegisterSelectRoleFragment())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun toNameFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.flContainerRegister, RegisterAddNameFragment())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    override fun toEmailFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.flContainerRegister, RegisterAddEmailFragment())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    override fun toPasswordFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.flContainerRegister, RegisterAddPasswordFragment())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }
}