package com.oligark.flashapp.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.oligark.flashapp.R
import com.oligark.flashapp.databinding.ActivityProfileRegisterBinding






class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_profile_register)


    }
}