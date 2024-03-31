package com.ifs21028.mygmail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import com.google.android.material.snackbar.Snackbar
import com.ifs21028.mygmail.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        setupAction()

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Memilih Floating Button Add!", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
        }
    }
    private fun setupView() {
        binding.navView.setCheckedItem(R.id.nav_home)
        binding.inAppBar.toolbar.overflowIcon =
            ContextCompat.getDrawable(this, R.drawable.ic_more_vert)
        loadFragment(FLAG_FRAGMENT_HOME)
    }
    private fun setupAction() {
        binding.inAppBar.toolbar.setNavigationOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        binding.inAppBar.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_settings -> {
                    val text = "Memilih menu settings"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(this, text, duration)
                    toast.show()
                    true
                }
                R.id.action_logout -> {
                    val text = "Memilih menu logout"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(this, text, duration)
                    toast.show()
                    true
                }
                else -> true
            }
        }
        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    val text = "Memilih menu Utama!"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(this, text, duration)
                    toast.show()
                    true
                }
                R.id.nav_profile -> {
                    val text = "Memilih menu Promosi!"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(this, text, duration)
                    toast.show()
                    true
                }
                R.id.nav_notes -> {
                    val text = "Memilih menu Sosial!"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(this, text, duration)
                    toast.show()
                    true
                }
                R.id.nav_send -> {
                    val text = "Memilih menu Kirim!"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(this, text, duration)
                    toast.show()
                    true
                }
                R.id.nav_pending -> {
                    val text = "Memilih menu Terjadwal!"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(this, text, duration)
                    toast.show()
                    true
                }
                else -> true
            }
        }
        binding.inAppBar.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    loadFragment(FLAG_FRAGMENT_HOME)
                    true
                }
                R.id.navigation_dashboard -> {
                    loadFragment(FLAG_FRAGMENT_DASHBOARD)
                    true
                }
                else -> true
            }
        }
    }
    private fun loadFragment(flag: String, message: String? = null) {
        val fragmentManager = supportFragmentManager
        val fragmentContainerId =
            binding.inAppBar.inContentMain.frameContainer.id
        when (flag) {
            FLAG_FRAGMENT_HOME -> {
                binding.inAppBar.bottomNavView
                    .menu
                    .findItem(R.id.navigation_home)
                    .setChecked(true)
                val homeFragment = HomeFragment()
                val bundle = Bundle().apply {
                    this.putString(
                        HomeFragment.EXTRA_MESSAGE,
                        message ?: "Belum ada menu yang dipilih!"
                    )
                }
                homeFragment.arguments = bundle
                fragmentManager
                    .beginTransaction()
                    .replace(
                        fragmentContainerId,
                        homeFragment,
                        HomeFragment::class.java.simpleName
                    )
                    .commit()
            }
            FLAG_FRAGMENT_DASHBOARD -> {
                val dashboardFragment = DashboardFragment()
                val fragment = fragmentManager
                    .findFragmentByTag(DashboardFragment::class.java.simpleName)
                if (fragment !is DashboardFragment) {
                    fragmentManager
                        .beginTransaction()
                        .replace(
                            fragmentContainerId,
                            dashboardFragment,
                            DashboardFragment::class.java.simpleName
                        )
                        .commit()
                }
            }
        }
    }
    companion object {
        const val FLAG_FRAGMENT_HOME = "fragment_home"
        const val FLAG_FRAGMENT_DASHBOARD = "fragment_dashboard"
    }
}

