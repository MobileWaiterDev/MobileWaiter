package com.mwaiterdev.waiter.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.navigation.NavigationView
import com.mwaiterdev.waiter.R
import com.mwaiterdev.waiter.databinding.ActivityMainBinding
import com.mwaiterdev.waiter.ui.bills.BillsFragment

class MainActivity : AppCompatActivity(), TitleToolbarListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private val viewBinding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(viewBinding.appBarMain.toolbar)

        initNavigation()
    }

    private fun initNavigation() {
        val drawerLayout: DrawerLayout = viewBinding.drawerLayout
        val navView: NavigationView = viewBinding.navView
        val navController = findNavController(R.id.container)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_bills, R.id.nav_login
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.container)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun updateTitle(title: String) {
        viewBinding.appBarMain.toolbar.title = title
    }

    override fun showToolBar(isVisible: Boolean) {
        if (isVisible) {
            this.supportActionBar?.show()
        } else {
            this.supportActionBar?.hide()
        }
    }



    override fun onBackPressed() {
        val navController = findNavController(R.id.container)
       navController.popBackStack(R.id.nav_bills, true)
    }
}