package com.ben.contactsappkotlin.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import com.ben.contactsappkotlin.R
import com.ben.contactsappkotlin.data.Person
import com.ben.contactsappkotlin.data.PersonViewModel
import com.ben.contactsappkotlin.listeners.ItemClick
import com.ben.contactsappkotlin.ui.components.PersonListAdapter
import com.ben.contactsappkotlin.utils.Constants
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, ItemClick {

    private var personListAdapter: PersonListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        if (drawer_layout != null) {
            val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
            )
            drawer_layout!!.addDrawerListener(toggle)
            toggle.syncState()
        }

        nav_view.setNavigationItemSelectedListener(this)

        personListAdapter = PersonListAdapter()
        personListAdapter!!.setListener(this)
        personList?.setHasFixedSize(true)
        val layout = LinearLayoutManager(this)
        layout.orientation = LinearLayout.VERTICAL
        personList?.layoutManager = layout
        personList?.adapter = personListAdapter

        val viewModel: PersonViewModel = ViewModelProviders.of(this).get(PersonViewModel::class.java)
        val data: LiveData<List<Person>> = viewModel.getData()
        data.observe(this, Observer {
            if (it != null) {
                personListAdapter!!.setData(it)
            }
        })
    }

    override fun onBackPressed() {
        if (drawer_layout != null && drawer_layout!!.isDrawerOpen(GravityCompat.START)) {
            drawer_layout!!.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

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

        if (drawer_layout != null) {
            drawer_layout!!.closeDrawer(GravityCompat.START)
        }
        return true
    }

    override fun onItemClick(view: View, position: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(Constants.ID, position)
        startActivity(intent)
    }
}
