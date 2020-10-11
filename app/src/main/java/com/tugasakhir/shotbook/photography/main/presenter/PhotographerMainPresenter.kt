package com.tugasakhir.shotbook.photography.main.presenter

import androidx.fragment.app.Fragment

class PhotographerMainPresenter(val listener: Listener) {
    interface Listener{
        fun goFragment(fm: Fragment)
    }

    fun goToFragment(fm: Fragment){
        listener.goFragment(fm)
    }
}