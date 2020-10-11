package com.tugasakhir.shotbook.photography.profile.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.tugasakhir.shotbook.photography.profile.view.ProfileAboutFragment
import com.tugasakhir.shotbook.photography.profile.view.ProfilePackageFragment
import com.tugasakhir.shotbook.photography.profile.view.ProfilePortofolioFragment

class PhotographerTabAdapter(fm: FragmentManager, behavior: Int, val data: Map<String, Any>, val usrID: String) :
    FragmentStatePagerAdapter(fm, behavior) {
    private val tabName: Array<String> = arrayOf("About Me", "Portofolio", "Package")

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> ProfileAboutFragment(data, usrID)
        1 -> ProfilePortofolioFragment()
        2 -> ProfilePackageFragment()
        else -> ProfileAboutFragment(data, usrID)
    }

    override fun getCount(): Int = 3
    override fun getPageTitle(position: Int): CharSequence? = tabName[position]
}