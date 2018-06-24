

package com.oligark.flashapp.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import com.oligark.flashapp.BR
import com.oligark.flashapp.R
import com.oligark.flashapp.databinding.ActivityLoginBinding
import com.oligark.flashapp.di.Dependencies
import com.oligark.flashapp.viewmodel.LoginViewModel
import com.oligark.flashapp.R.id.textView
import android.widget.TextView




class LoginActivity : AppCompatActivity() {
    companion object {
        var TAG = LoginActivity::class.simpleName
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
                LoginViewModel.LoginStatus.LOADING -> {
                    binding.signinLoading.progressOverlay.visibility = View.VISIBLE
                }
                LoginViewModel.LoginStatus.COMPLETE -> {
                    binding.signinLoading.progressOverlay.visibility = View.GONE
                }
                LoginViewModel.LoginStatus.ERROR -> {
                    Toast.makeText(this, viewModel.errorMessage, Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "Login error")
                }
                LoginViewModel.LoginStatus.SUCCESS -> {
                    Log.d(TAG, "Login success")
                    var intent = Intent(this, UserSelectionActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else -> {
                    Log.d(TAG, "Login else")
                }
            }
        })

        viewModel.currentUser.observe(this, Observer { user ->
            getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE).edit()
                    .putString("userJson", Dependencies.getInstance().gson.toJson(user))
                    .apply()
        })

        viewModel.googleSignInProgress.observe(this, Observer { googleSignIn ->
            Log.d(TAG, "google sign in updated")
            when (googleSignIn) {
                true -> {
                    Log.d(TAG, "Started google sign in")
                    val intent = viewModel.googleSignInIntent
                    startActivityForResult(intent, LoginViewModel.RC_GOOGLE_SIGN_IN)
                }
                else -> {}
            }
        })

        viewModel.potentialUser.observe(this, Observer { user ->
            val intent = Intent(this, RegisterActivity::class.java)
            intent.putExtra("userJson", Dependencies.getInstance().gson.toJson(user))
            startActivity(intent)
        })

        binding.registerLink.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        viewModel.onActivityResult(requestCode, resultCode, data)
    }
}
