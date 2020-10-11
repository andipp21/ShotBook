package com.tugasakhir.shotbook.auth.halamanLogin.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.tugasakhir.shotbook.R
import com.tugasakhir.shotbook.auth.halamanLogin.presenter.LoginPresenter
import com.tugasakhir.shotbook.photography.main.view.PhotographerMainActivity
import com.tugasakhir.shotbook.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity(), LoginPresenter.Listener {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var presenter: LoginPresenter
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        mAuth = FirebaseAuth.getInstance()
        presenter = LoginPresenter(this, mAuth, this)

        setContentView(binding.root)

        presenter.stateChecker()

        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                binding.etEmail.removeTextChangedListener(this)

                presenter.setEmail(s.toString())

                binding.etEmail.text?.length?.let { binding.etEmail.setSelection(it) }
                binding.etEmail.addTextChangedListener(this)
            }

        })

        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                binding.etPassword.removeTextChangedListener(this)

                presenter.setPassword(s.toString())

                binding.etPassword.text?.length?.let { binding.etPassword.setSelection(it) }
                binding.etPassword.addTextChangedListener(this)
            }

        })

        binding.btnLogin.setOnClickListener {
            presenter.login()
        }
    }

    override fun buttonEnabled() {
        binding.btnLogin.isClickable = true
        binding.btnLogin.setBackgroundResource(R.drawable.button_enabled)
    }

    override fun buttonDisabled() {
        binding.btnLogin.isClickable = false
        binding.btnLogin.setBackgroundResource(R.drawable.button_disabled)
    }

    override fun goPhotographyPage(id:String) {
        val intent = Intent(this, PhotographerMainActivity::class.java)
        intent.putExtra("user id", id)
        startActivity(intent)
    }
}