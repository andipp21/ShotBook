package com.tugasakhir.shotbook.photography.profile.presenter

class ProfilePortofolioPresenter(val listener: Listener) {
    interface Listener{

    }

    var portofolio: List<String>? = null

    fun setData(dt: Map<String, Any>) {
        portofolio = dt["portofolio_image"] as List<String>?
    }
}