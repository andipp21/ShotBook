package com.tugasakhir.shotbook.photography.profile.view.profilPackage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tugasakhir.shotbook.databinding.ActivityAddPackageBinding

class AddPackageActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddPackageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPackageBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}