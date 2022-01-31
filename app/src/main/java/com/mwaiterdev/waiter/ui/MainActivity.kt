package com.mwaiterdev.waiter.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.navigation.NavigationView
import com.mwaiterdev.domain.models.User
import com.mwaiterdev.waiter.BuildConfig
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

        init()
    }

    private fun init() {
        viewBinding.version.text = String.format(
            VERSION_STRING_TEMPLATE,
            BuildConfig.VERSION_CODE,
            BuildConfig.VERSION_NAME
        )

        viewBinding.exitButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(BillsFragment.APP_TITLE)
                .setMessage(BillsFragment.APP_CLOSE_QUESTIONS)
                .setIcon(R.drawable.ic_launcher_foreground)
                .setPositiveButton(BillsFragment.DIALOG_OK_BUTTON_TEXT) { _, _ ->
                    finish()
                }
                .setNegativeButton(BillsFragment.DIALOG_CANCEL_BUTTON_TEXT) { dialog, id ->
                    dialog.cancel()
                }
            builder.create()
            builder.show()
        }
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
        viewBinding.appBarMain.title.text = title
        viewBinding.appBarMain.topTitle.text = ""
        viewBinding.appBarMain.downTitle.text = ""
    }

    override fun setMultiLineTitle(topTitle: String, downTitle: String) {
        viewBinding.appBarMain.title.text = ""
        viewBinding.appBarMain.topTitle.text = topTitle
        viewBinding.appBarMain.downTitle.text = downTitle
    }

    override fun showToolBar(isVisible: Boolean) {
        if (isVisible) {
            this.supportActionBar?.show()
        } else {
            this.supportActionBar?.hide()
        }
    }

    override fun setUser(user: User) {
        val headerView: View = viewBinding.navView.getHeaderView(HEADER_VIEW_INDEX)
        headerView.findViewById<TextView>(R.id.user_group).text = user.groupName
        headerView.findViewById<TextView>(R.id.user_name).text = user.name
    }

    companion object {
        const val HEADER_VIEW_INDEX = 0
        const val VERSION_STRING_TEMPLATE = "Версия: %s.%s"
    }
}