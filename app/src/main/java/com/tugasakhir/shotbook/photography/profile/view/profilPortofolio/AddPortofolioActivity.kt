package com.tugasakhir.shotbook.photography.profile.view.profilPortofolio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tugasakhir.shotbook.databinding.ActivityAddPortofolioBinding

class AddPortofolioActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddPortofolioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPortofolioBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}