package com.varsha.ezetapassignment

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class FirstScreen : AppCompatActivity() {
    private  var mIsShowPass=false
    private lateinit var username:EditText
    private lateinit var userpassword:EditText
    private lateinit var useremail:EditText
    private lateinit var dob:EditText
    var age=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SharedPref.init(this)

        val calendar=Calendar.getInstance()
        val myYear=calendar.get(Calendar.YEAR)
        val myMonth=calendar.get(Calendar.MONTH)
        val day=calendar.get(Calendar.DAY_OF_MONTH)


        val c=Calendar.getInstance()
        val Year=c.get(Calendar.YEAR)

        imageBirthDate.setOnClickListener {
            val datePickerDialog=DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
             val selectedDate="$dayOfMonth/$month/$year"
                etBirthDate.setText(selectedDate)
                //calculate Age
                age=Year-year


            },myYear,myMonth,day)
            datePickerDialog.show()
        }


    // Validation all Field
     btnProceed.setOnClickListener {
         val username =etTitle.text.toString().trim()
         val userpassword =etPassword.text.toString().trim()
         val useremail =etEmail.text.toString().trim()
         val dob =etBirthDate.text.toString().trim()
         if (username.isEmpty()){
             etTitle.error="Username Required"
             return@setOnClickListener
         }else if(useremail.isEmpty()){
             etEmail.error="Email Required"
             return@setOnClickListener
         }else if(userpassword.isEmpty()){
             etPassword.error="Password Required"
             return@setOnClickListener
         }else if(dob.isEmpty()){
             etBirthDate.error="Date of Birth Required"
             return@setOnClickListener
         }
         else{
             Toast.makeText(this,"Validation Completed",Toast.LENGTH_SHORT).show()
         }
         saveData()
         val intent = Intent(this@FirstScreen, HomeActivity::class.java)
         startActivity(intent)
     }





            //password toggle
        imagePassword.setOnClickListener {
            mIsShowPass=!mIsShowPass
            showPassword(mIsShowPass)
        }
        showPassword(mIsShowPass)



    }
    private fun showPassword(isShow:Boolean){
        if (isShow){
            etPassword.transformationMethod=HideReturnsTransformationMethod.getInstance()
            imagePassword.setImageResource(R.drawable.ic_show_password_24)
        }else{
            etPassword.transformationMethod=PasswordTransformationMethod.getInstance()
            imagePassword.setImageResource(R.drawable.ic_baseline_visibility_off_24)
        }
        etPassword.setSelection(etPassword.text.toString().length)
    }

    //get Data from Shared Preference
    private fun saveData() {
        SharedPref.write(SharedPref.NAME, etTitle.text.toString())
        SharedPref.write(SharedPref.EMAIL, etEmail.text.toString())
        SharedPref.write(SharedPref.PASSWORD, etPassword.text.toString())
        SharedPref.write(SharedPref.DOB, etBirthDate.text.toString())
        SharedPref.write(SharedPref.AGE, age.toString())
    }
}