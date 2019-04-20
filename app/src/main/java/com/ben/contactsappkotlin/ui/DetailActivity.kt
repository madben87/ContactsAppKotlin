package com.ben.contactsappkotlin.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ben.contactsappkotlin.R
import com.ben.contactsappkotlin.data.Person
import com.ben.contactsappkotlin.data.PersonViewModel
import com.ben.contactsappkotlin.ui.components.MainPagerAdapter
import com.ben.contactsappkotlin.utils.Constants
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private var adapter: MainPagerAdapter? = null
    private var currentId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        if (intent != null && intent.hasExtra(Constants.ID)) {
            currentId = intent.getIntExtra(Constants.ID, 0)
        }

        adapter = MainPagerAdapter(supportFragmentManager)

        val viewModel: PersonViewModel = ViewModelProviders.of(this).get(PersonViewModel::class.java)
        val data: LiveData<List<Person>> = viewModel.getData()
        data.observe(this, Observer {
            if (it != null) {
                with(it) {
                    forEach {
                        adapter!!.addFragment(DetailFragment.newInstance(it), it.name)
                    }
                }
                mainViewPager.adapter = adapter
                mainViewPager.currentItem = currentId
            }
        })
    }
}
