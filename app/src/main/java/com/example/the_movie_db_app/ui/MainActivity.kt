package com.example.the_movie_db_app.ui

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.the_movie_db_app.R
import com.example.the_movie_db_app.utils.Commons
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

/**
 * Created by Romel Boada on 05/06/19.
 */

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val navController: NavController by lazy {
        Navigation.findNavController(this, R.id.container_home)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadNavigationView()
    }

    private fun loadNavigationView() {

        toolbar.title = getString(R.string.app_name)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        nav_view.itemIconTintList = null
        navController.navigate(R.id.movieFragment)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_movie -> {
                navController.navigate(R.id.movieFragment)
                toolbar.title = getString(R.string.movie)
            }
            R.id.nav_serie -> {
                navController.navigate(R.id.serieFragment)
                toolbar.title = getString(R.string.serie)
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
