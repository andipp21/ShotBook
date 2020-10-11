package com.tugasakhir.shotbook.photography.profile.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import com.bumptech.glide.Glide
import com.tugasakhir.shotbook.R
import com.tugasakhir.shotbook.photography.main.view.PhotographerMainActivity
import com.tugasakhir.shotbook.photography.profile.adapter.PhotographerTabAdapter
import com.tugasakhir.shotbook.photography.profile.presenter.ProfilePresenter
import kotlinx.android.synthetic.main.fragment_photographer_profile.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PhotographerProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PhotographerProfileFragment : Fragment(), ProfilePresenter.Listener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var usrID: String? = null

    lateinit var presenter: ProfilePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            usrID = it.getString("usrID")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_photographer_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = ProfilePresenter(this)

        (activity as PhotographerMainActivity).supportActionBar?.title = "Profile"
    }

    override fun onResume() {
        super.onResume()

        GlobalScope.launch {
            usrID?.let { presenter.getAllData(it) }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PhotographerProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PhotographerProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

        fun userID(usrID: String) =
            PhotographerProfileFragment().apply {
                arguments = Bundle().apply {
                    putString("usrID", usrID)
                }
            }
    }

    override fun showData(data: Map<String, Any>) {
        val name = "${data["first_name"]} ${data["last_name"]}"
        val city = data["city"]
        val picture = if (data["profile_picture"] == null) {
            "https://firebasestorage.googleapis.com/v0/b/jastip-21e34.appspot.com/o/toko%2FVAVA.jpg?alt=media&token=cfbc1eaa-f0bb-4d7d-9d66-9182e5d967d9"
        } else {
            data["profile_picture"]
        }

        Glide.with(this)
            .load(picture)
            .circleCrop()
            .into(ivPhotographerPhotoProfil)

        namaPhotographer.text = name
        kotaTinggal.text = city.toString()

        val adapter = PhotographerTabAdapter(
            childFragmentManager,
            BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
            data,
            usrID!!
        )

        viewPagerProfile.adapter = adapter
        tabLayout.setupWithViewPager(viewPagerProfile)
    }
}