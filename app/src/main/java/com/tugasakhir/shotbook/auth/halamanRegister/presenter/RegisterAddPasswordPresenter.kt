package com.tugasakhir.shotbook.auth.halamanRegister.presenter

class RegisterAddPasswordPresenter(private val listener: Listener) {
    interface Listener{
        fun showAngkaPasswordTrue()
        fun showAngkaPasswordFalse()
        fun showHurufKecilTrue()
        fun showHurufKecilFalse()
        fun showHurufBesarTrue()
        fun showHurufBesarFalse()
        fun showMinCharTrue()
        fun showMinCharFalse()

        fun showPasswordChecker()
        fun dismissPasswordChecker()

        fun showConfirmError()
        fun dismissConfirmError()

        fun enableButton()
        fun disableButton()
    }

    private var stateCheckHurufKecil = false
    private var stateCheckAngka = false
    private var stateCheckHurufKapital = false
    private var stateCheckMinChar = false

    private var statePassword = false
    private var stateConfirmPassword = false

    private var password = ""

    fun checkPass(et: String) {
        stateCheckAngka = if (et.matches((".*\\d.*").toRegex())) {
            listener.showAngkaPasswordTrue()
            true
        } else {
            listener.showAngkaPasswordFalse()
            false
        }

        stateCheckHurufKecil = if (et.matches((".*[a-z].*").toRegex())) {
            listener.showHurufKecilTrue()
            true
        } else {
            listener.showHurufKecilFalse()
            false
        }

        stateCheckHurufKapital = if (et.matches((".*[A-Z].*").toRegex())) {
            listener.showHurufBesarTrue()
            true
        } else {
            listener.showHurufBesarFalse()
            false
        }

        stateCheckMinChar = if (et.length >= 8) {
            listener.showMinCharTrue()
            true
        } else {
            listener.showMinCharFalse()
            false
        }

        password = et

        checkState()

    }

    fun checkConfirmPassword(et : String){
        stateConfirmPassword = if (et == password){
            listener.dismissConfirmError()
            true
        } else {
            listener.showConfirmError()
            false
        }

        checkStateButton()
    }

    private fun checkState(){
        if (stateCheckAngka && stateCheckHurufKapital && stateCheckHurufKecil && stateCheckMinChar){
            listener.dismissPasswordChecker()
            statePassword = true
        } else {
            statePassword = false
            listener.showPasswordChecker()
        }

        checkStateButton()
    }

    fun checkStateButton(){
        if (statePassword && stateConfirmPassword){
            listener.enableButton()
        } else {
            listener.disableButton()
        }
    }
}