package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class StaffActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staff)

        //Staff
        val navView1: BottomNavigationView = findViewById(R.id.nav_view1)

        val navController = findNavController(R.id.nav_host_fragment1)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration1 = AppBarConfiguration(setOf(
            R.id.navigation_staff_Home, R.id.navigation_StaffeditSchedule, R.id.navigation_StaffAccount))
        setupActionBarWithNavController(navController, appBarConfiguration1)
        navView1.setupWithNavController(navController)

        NavigationUI.setupActionBarWithNavController(this,navController)
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment1)
        return navController.navigateUp()
    }
}
