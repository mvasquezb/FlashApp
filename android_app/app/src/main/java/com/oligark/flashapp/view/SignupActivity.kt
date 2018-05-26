package com.oligark.flashapp.view

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.oligark.flashapp.R
//import com.oligark.flashapp.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

//    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        setContentView(R.layout.activity_signup)
    }
}
