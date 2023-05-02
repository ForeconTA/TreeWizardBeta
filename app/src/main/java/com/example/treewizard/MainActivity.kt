package com.example.treewizard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.treewizard.ui.home.HomeFragment
import com.example.treewizard.ui.dashboard.DashboardFragment
import com.example.treewizard.ui.notifications.NotificationsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment()).commit()

        val bottomNavigationView = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.nav_view)

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment()).commit()
                    true
                }
                R.id.navigation_dashboard -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, DashboardFragment()).commit()
                    true
                }
                R.id.navigation_notifications -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, NotificationsFragment()).commit()
                    true
                }
                else -> false
            }
        }

    }
}
