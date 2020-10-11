package com.tugasakhir.shotbook.photography.profile.view.profilPackage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tugasakhir.shotbook.databinding.ActivityEditPackageBinding

class EditPackageActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditPackageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditPackageBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}