package com.tugasakhir.shotbook.photography.profile.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tugasakhir.shotbook.R
import com.tugasakhir.shotbook.photography.profile.presenter.ProfileAboutPresenter
import kotlinx.android.synthetic.main.fragment_profile_about.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileAboutFragment] factory method to
 * create an instance of this fragment.
 */
class ProfileAboutFragment(private val data: Map<String, Any>, private val userID: String) :
    Fragment(), ProfileAboutPresenter.Listener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
//
//    private var usrID: String? = null

    lateinit var presenter: ProfileAboutPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
//            usrID = it.getString("usrID")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = ProfileAboutPresenter(this)

        Log.d("Data User Profile", "$data")

        presenter.setData(data)
//


        disableButton()

        etFirstName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                enableButton()
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        etLastName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                enableButton()
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                enableButton()
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        etCity.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                enableButton()
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        etPhoneNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                enableButton()
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        etAboutMePhotographer.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                enableButton()
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    fun enableButton() {
        btnSaveAboutMe.setBackgroundResource(R.drawable.button_enabled)
        btnSaveAboutMe.isClickable = true

        btnSaveAboutMe.setOnClickListener {
            GlobalScope.launch {
                val data: MutableMap<String, Any> = HashMap()
                data["first_name"] = etFirstName.text.toString()
                data["last_name"] = etLastName.text.toString()
                data["email"] = etEmail.text.toString()
                data["city"] = etCity.text.toString()
                data["phone_number"] = etPhoneNumber.text.toString()
                data["about"] = etAboutMePhotographer.text.toString()

                presenter.save(data, userID)
            }
        }
    }

    fun disableButton() {
        btnSaveAboutMe.setBackgroundResource(R.drawable.button_enabled)
        btnSaveAboutMe.isClickable = false
    }

    override fun showData(data: Map<String, Any>) {
        if (data["first_name"] != null) {
            etFirstName.setText(
                data["first_name"].toString()
            )
        }

        if (data["last_name"] != null) {
            etLastName.setText(
                data["last_name"].toString()
            )
        }

        if (data["email"] != null) {
            etEmail.setText(
                data["email"].toString()
            )
        }

        if (data["city"] != null) {
            etCity.setText(
                data["city"].toString()
            )
        }

        if (data["phone_number"] != null) {
            etPhoneNumber.setText(
                data["phone_number"].toString()
            )
        }

        if (data["about"] != null) {
            etAboutMePhotographer.setText(
                data["about"].toString()
            )
        }
    }

//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment ProfileAboutFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            ProfileAboutFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//
//        fun userID(usrID: String?) =
//            PhotographerProfileFragment().apply {
//                arguments = Bundle().apply {
//                    putString("usrID", usrID)
//                }
//            }
//
//        fun dataUser(data : Map<String, Any>) =
//            ProfileAboutFragment().apply {
//                arguments = Bundle().apply {
//                    putString("firstName", "${data["first_name"]}")
//                    putString("lastName", "${data["last_name"]}")
//                    putString("email", "${data["email"]}")
//                    putString("city", "${data["city"]}")
//                    putString("phoneNumber", "${data["phone_number"]}")
//                    putString("about", "${data["about"]}")
//                }
//            }
//    }
}