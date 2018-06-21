package com.oligark.flashapp.view

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.oligark.flashapp.R
import com.oligark.flashapp.databinding.ActivityUserSelectionBinding

class UserSelectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserSelectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_selection)

        binding.customerBtn.setOnClickListener { v ->
            // TODO: Save selection to user preferences
            val intent = Intent(this, CustomerMainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.sitterBtn.setOnClickListener { v ->
            // TODO: Save selection to user preferences
            Toast.makeText(this, getString(R.string.user_option_not_available), Toast.LENGTH_SHORT).show()
//            val intent = Intent(this, SitterMainActivity::class.java)
//            startActivity(intent)
//            finish()
        }
    }
}
