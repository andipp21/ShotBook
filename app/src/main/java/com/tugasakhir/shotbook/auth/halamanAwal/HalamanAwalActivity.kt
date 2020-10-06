package com.tugasakhir.shotbook.auth.halamanAwal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tugasakhir.shotbook.auth.halamanRegister.view.RegisterActivity
import com.tugasakhir.shotbook.databinding.ActivityHalamanAwalBinding

class HalamanAwalActivity : AppCompatActivity() {
    lateinit var binding: ActivityHalamanAwalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHalamanAwalBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}