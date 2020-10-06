package com.tugasakhir.shotbook.auth.halamanRegister.view.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import com.tugasakhir.shotbook.R
import com.tugasakhir.shotbook.auth.halamanRegister.presenter.RegisterAddPasswordPresenter
import com.tugasakhir.shotbook.auth.halamanRegister.view.RegisterActivity
import kotlinx.android.synthetic.main.fragment_register_add_password.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterAddPasswordFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterAddPasswordFragment : Fragment(), RegisterAddPasswordPresenter.Listener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var presenter: RegisterAddPasswordPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_add_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = RegisterAddPasswordPresenter(this)

        presenter.checkStateButton()

        dismissConfirmError()
        dismissPasswordChecker()

        etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                etPassword.removeTextChangedListener(this)

                presenter.checkPass(p0.toString())

                etPassword.text?.length?.let { etPassword.setSelection(it) }
                etPassword.addTextChangedListener(this)
            }
        })

        etConfirmPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                etConfirmPassword.removeTextChangedListener(this)

                presenter.checkConfirmPassword(p0.toString())

                etConfirmPassword.text?.length?.let { etConfirmPassword.setSelection(it) }
                etConfirmPassword.addTextChangedListener(this)
            }

        })

        btnContinue.setOnClickListener {
            val password = etPassword.text.toString()

            (activity as RegisterActivity).presenter.setPasswordAccount(password)

            (activity as RegisterActivity).presenter.showLog()

            (activity as RegisterActivity).presenter.register()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RegisterAddPasswordFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterAddPasswordFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun showAngkaPasswordTrue() {
        tvAngka.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_password_oke, 0,0 ,0)
        tvAngka.setTextColor(getColor((activity as RegisterActivity),R.color.colorPrimary))
    }

    override fun showAngkaPasswordFalse() {
        tvAngka.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_password_no, 0,0 ,0)
        tvAngka.setTextColor(getColor((activity as RegisterActivity),R.color.colorRed))
    }

    override fun showHurufKecilTrue() {
        tvHurufKecil.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_password_oke, 0,0 ,0)
        tvHurufKecil.setTextColor(getColor((activity as RegisterActivity),R.color.colorPrimary))
    }

    override fun showHurufKecilFalse() {
        tvHurufKecil.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_password_no, 0,0 ,0)
        tvHurufKecil.setTextColor(getColor((activity as RegisterActivity),R.color.colorRed))
    }

    override fun showHurufBesarTrue() {
        tvHurufKapital.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_password_oke, 0,0 ,0)
        tvHurufKapital.setTextColor(getColor((activity as RegisterActivity),R.color.colorPrimary))
    }

    override fun showHurufBesarFalse() {
        tvHurufKapital.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_password_no, 0,0 ,0)
        tvHurufKapital.setTextColor(getColor((activity as RegisterActivity),R.color.colorRed))
    }

    override fun showMinCharTrue() {
        tvMinKarakter.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_password_oke, 0,0 ,0)
        tvMinKarakter.setTextColor(getColor((activity as RegisterActivity),R.color.colorPrimary))
    }

    override fun showMinCharFalse() {
        tvMinKarakter.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_password_no, 0,0 ,0)
        tvMinKarakter.setTextColor(getColor((activity as RegisterActivity),R.color.colorRed))
    }

    override fun showPasswordChecker() {
        passwordChecker.visibility = View.VISIBLE
    }

    override fun dismissPasswordChecker() {
        passwordChecker.visibility = View.GONE
    }

    override fun showConfirmError() {
        confirmPasswordWarn.visibility = View.VISIBLE
    }

    override fun dismissConfirmError() {
        confirmPasswordWarn.visibility = View.GONE
    }

    override fun enableButton() {
        btnContinue.isClickable = true
        btnContinue.setBackgroundResource(R.drawable.button_enabled)
    }

    override fun disableButton() {
        btnContinue.isClickable = false
        btnContinue.setBackgroundResource(R.drawable.button_disabled)
    }
}