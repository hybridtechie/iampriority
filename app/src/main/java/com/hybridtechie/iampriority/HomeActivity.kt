package com.hybridtechie.iampriority

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*

class HomeActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private var mAdapter: HomeAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        initToolbar()
        initComponent()
    }

    private fun initToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_expand_arrow)
        //setSupportActionBar(toolbar)
        val title = supportActionBar?.setTitle("Alerts")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // Tools.setSystemBarColor(this, R.color.grey_1000)
    }

    private fun initComponent() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val items = ArrayList<Alerts>()
        items.add(Alerts(getString(R.string.small_text), R.drawable.government_health, "url", "Government"))
        items.add(Alerts(getString(R.string.small_text), R.drawable.government_defense, "url", "Government"))
        items.add(Alerts(getString(R.string.small_text), R.drawable.government_education, "url", "Government"))
        items.add(Alerts(getString(R.string.small_text), R.drawable.government_jobs, "url", "Government"))
        items.add(Alerts(getString(R.string.small_text), R.drawable.government_environment, "url", "Government"))
        items.add(Alerts(getString(R.string.small_text), R.drawable.government_energy, "url", "Government"))
        items.add(Alerts(getString(R.string.small_text), R.drawable.government_homeaffairs, "url", "Government"))
        items.add(Alerts(getString(R.string.small_text), R.drawable.government_humanservices, "url", "Government"))
        items.add(Alerts(getString(R.string.small_text), R.drawable.government_innovation, "url", "Government"))
        items.add(Alerts(getString(R.string.small_text), R.drawable.government_health, "url", "Government"))
        items.add(Alerts(getString(R.string.small_text), R.drawable.government_defense, "url", "Government"))
        items.add(Alerts(getString(R.string.small_text), R.drawable.government_education, "url", "Government"))
        items.add(Alerts(getString(R.string.small_text), R.drawable.government_jobs, "url", "Government"))
        items.add(Alerts(getString(R.string.small_text), R.drawable.government_environment, "url", "Deals"))
        items.add(Alerts(getString(R.string.small_text), R.drawable.government_energy, "url", "Deals"))
        items.add(Alerts(getString(R.string.small_text), R.drawable.government_homeaffairs, "url", "Deals"))
        items.add(Alerts(getString(R.string.small_text), R.drawable.government_humanservices, "url", "Deals"))
        items.add(Alerts(getString(R.string.small_text), R.drawable.government_innovation, "url", "Deals"))
        items.add(Alerts(getString(R.string.small_text), R.drawable.government_health, "url", "Deals"))
        items.add(Alerts(getString(R.string.small_text), R.drawable.government_defense, "url", "Deals"))
        items.add(Alerts(getString(R.string.small_text), R.drawable.government_education, "url", "Deals"))
        items.add(Alerts(getString(R.string.small_text), R.drawable.government_jobs, "url", "Deals"))
        items.add(Alerts(getString(R.string.small_text), R.drawable.government_environment, "url", "Deals"))
        items.add(Alerts(getString(R.string.small_text), R.drawable.government_energy, "url", "Deals"))
        items.add(Alerts(getString(R.string.small_text), R.drawable.government_homeaffairs, "url", "Deals"))


        //set data and list adapter
        mAdapter = HomeAdapter(this, items)
        recyclerView!!.adapter = mAdapter

        // on item list clicked
        mAdapter!!.setOnItemClickListener(object : HomeAdapter.OnItemClickListener {
            override fun onItemClick(view: View, obj: Alerts, position: Int) {
                Log.d("HomeActivity", "Clicked item at $position")
            }
        })

    }
}
