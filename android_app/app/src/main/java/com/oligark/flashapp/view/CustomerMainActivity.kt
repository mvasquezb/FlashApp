package com.oligark.flashapp.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.oligark.flashapp.R
import com.oligark.flashapp.databinding.ActivityCustomerMainBinding

class CustomerMainActivity : AppCompatActivity(),
        NavigationView.OnNavigationItemSelectedListener,
        PetListFragment.PetListCallback,
        CustomerHomeFragment.CustomerHomeCallback {

    private lateinit var binding: ActivityCustomerMainBinding
    var fragment: Fragment? = null
    var fm = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_customer_main)
        setSupportActionBar(binding.appBar.toolbar)

        replaceFragment(CustomerHomeFragment())

        var toggle = ActionBarDrawerToggle(
                this,
                binding.drawerLayout,
                binding.appBar.toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
    fun onClickService(v: View){
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.customer_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                replaceFragment(CustomerHomeFragment())
            }
            R.id.nav_history -> {
                replaceFragment(UserHistoryFragment(), addToBackStack = true)
            }
            R.id.nav_pets -> {
                replaceFragment(PetListFragment(), addToBackStack = true)
//                var intent = Intent(this, PetListActivity::class.java)
//                startActivity(intent)
            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun replaceFragment(fragment: Fragment,
                                addToBackStack: Boolean = false) {
        supportFragmentManager.beginTransaction().apply {

            replace(R.id.customerContent, fragment)
            if (addToBackStack) {
                this.addToBackStack(null)
            }
        }.commit()
    }

    override fun addPetButtonClick() {
        replaceFragment(NewPetFragment(), addToBackStack = true)
    }

    override fun onAddServiceClick() {

        replaceFragment(ServiceCategoryFragment(), addToBackStack = true)
//        replaceFragment(ServiceDetailFragment(), addToBackStack = true)
    }
}
