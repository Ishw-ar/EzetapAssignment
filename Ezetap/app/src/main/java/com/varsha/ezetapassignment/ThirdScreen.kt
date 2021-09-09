package com.varsha.ezetapassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_third_screen.*

class ThirdScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third_screen)
        SharedPref.init(this)

        tvName.text = SharedPref.read(SharedPref.NAME, "")
        tvEmail.text = SharedPref.read(SharedPref.EMAIL, "")
        tvPassword.text = SharedPref.read(SharedPref.PASSWORD, "")
        tvBirthDate.text = SharedPref.read(SharedPref.DOB, "")
        tvAddressLatitude.text = SharedPref.read(SharedPref.LATTITUDE, "")
        AddressLongitude.text = SharedPref.read(SharedPref.LONGITUDE, "")
        tvAge.text = SharedPref.read(SharedPref.AGE, "")

    }
}