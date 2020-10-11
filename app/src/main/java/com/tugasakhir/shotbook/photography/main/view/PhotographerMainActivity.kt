package com.tugasakhir.shotbook.photography.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.tugasakhir.shotbook.R
import com.tugasakhir.shotbook.photography.home.view.PhotographerHomeFragment
import com.tugasakhir.shotbook.photography.inbox.view.PhotographerInboxFragment
import com.tugasakhir.shotbook.photography.main.presenter.PhotographerMainPresenter
import com.tugasakhir.shotbook.photography.order.view.PhotographerOrderFragment
import com.tugasakhir.shotbook.photography.profile.view.PhotographerProfileFragment
import com.tugasakhir.shotbook.databinding.ActivityPhotographerMainBinding
import kotlinx.android.synthetic.main.activity_photographer_main.*

class PhotographerMainActivity : AppCompatActivity(), PhotographerMainPresenter.Listener {
    private lateinit var binding: ActivityPhotographerMainBinding
    lateinit var presenter: PhotographerMainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotographerMainBinding.inflate(layoutInflater)
        presenter = PhotographerMainPresenter(this)

        setContentView(binding.root)

        val usrID = intent.getStringExtra("user id")

        val appBar = binding.appBarLayout.toolbar
        setSupportActionBar(appBar)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.flPhotographerMain, PhotographerHomeFragment())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        botNavPhotographer.setOnNavigationItemSelectedListener {
            return@setOnNavigationItemSelectedListener when(it.itemId){
                R.id.photographerHome -> {
                    presenter.goToFragment(PhotographerHomeFragment())
                    true
                }
                R.id.photographerInbox -> {
                    presenter.goToFragment(PhotographerInboxFragment())
                    true
                }
                R.id.photographerOrder -> {
                    presenter.goToFragment(PhotographerOrderFragment())
                    true
                }
                R.id.photographerProfile -> {
                    if (usrID != null){
                        presenter.goToFragment(PhotographerProfileFragment.userID(usrID))
                    }
                    true
                }
                else -> false
            }
        }
    }

    override fun goFragment(fm: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.flPhotographerMain, fm)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }
}