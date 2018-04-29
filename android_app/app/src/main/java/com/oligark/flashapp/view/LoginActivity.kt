package com.oligark.flashapp.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import com.oligark.flashapp.BR
import com.oligark.flashapp.R
import com.oligark.flashapp.databinding.ActivityLoginBinding
import com.oligark.flashapp.viewmodel.LoginViewModel


class LoginActivity : AppCompatActivity() {
    companion object {
        val TAG = LoginActivity::class.simpleName
    }

    private lateinit var viewModel: LoginViewModel

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        binding.setVariable(BR.viewModel, viewModel)

        initView()
    }

    private fun initView() {
        viewModel.loginStatus.observe(this, Observer { status ->
            when (status) {
                LoginViewModel.LoginStatus.LOADING -> binding.loginProgress.visibility = View.VISIBLE
                LoginViewModel.LoginStatus.COMPLETE -> binding.loginProgress.visibility = View.GONE
                LoginViewModel.LoginStatus.ERROR -> {
                    Log.d(TAG, "Login error")
                }
                LoginViewModel.LoginStatus.SUCCESS -> {
                    Log.d(TAG, "Login success")
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                else -> {
                    Log.d(TAG, "Login else")
                }
            }
        })

        binding.password.setOnEditorActionListener { v, actionId, event ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    viewModel.onLogin(LoginViewModel.LoginType.EMAIL)
                    true
                }
                else -> false
            }
        }

    }

}
