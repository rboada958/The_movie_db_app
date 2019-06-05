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

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val navController: NavController by lazy {
        Navigation.findNavController(this, R.id.container_home)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadNavigationView()
    }

    fun loadNavigationView() {

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        navController.navigate(R.id.movieFragment)
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                navController.navigate(R.id.movieFragment)
                Commons.makeToast("nav_home", this)
            }
            R.id.nav_gallery -> {
                navController.navigate(R.id.movieFragment)
                Commons.makeToast("nav_gallery", this)
            }
            R.id.nav_slideshow -> {
                navController.navigate(R.id.movieFragment)
                Commons.makeToast("nav_slideshow", this)
            }
            R.id.nav_tools -> {
                navController.navigate(R.id.movieFragment)
                Commons.makeToast("nav_tools", this)
            }
            R.id.nav_share -> {
                navController.navigate(R.id.movieFragment)
                Commons.makeToast("nav_share", this)
            }
            R.id.nav_send -> {
                navController.navigate(R.id.movieFragment)
                Commons.makeToast("nav_send", this)
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
