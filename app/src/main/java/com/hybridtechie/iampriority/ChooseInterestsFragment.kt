package com.hybridtechie.iampriority

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import java.util.*

class ChooseInterestsFragment : Fragment() {

    private var parent_view: View? = null

    private var recyclerView: RecyclerView? = null
    private var mAdapter: ChooseInterestsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_choose_interest, container, false)
        initComponent(view)

        return view
    }

    private fun initComponent(view: View) {

        val btnComplete = view.findViewById<MaterialButton>(R.id.btn_complete)
        btnComplete.setOnClickListener { goToHome() }

        recyclerView = view.findViewById(R.id.recyclerView) as RecyclerView
        recyclerView!!.layoutManager = GridLayoutManager(context, 3)
        recyclerView!!.setHasFixedSize(true)

        val items = ArrayList<Interests>()
        items.add(Interests("Health ", R.drawable.government_health, "url", "Government"))
        items.add(Interests("Defense", R.drawable.government_defense, "url", "Government"))
        items.add(Interests("Education", R.drawable.government_education, "url", "Government"))
        items.add(Interests("Jobs", R.drawable.government_jobs, "url", "Government"))
        items.add(Interests("Environment", R.drawable.government_environment, "url", "Government"))
        items.add(Interests("Energy", R.drawable.government_energy, "url", "Government"))
        items.add(Interests("Home Affairs", R.drawable.government_homeaffairs, "url", "Government"))
        items.add(Interests("Human Services", R.drawable.government_humanservices, "url", "Government"))
        items.add(Interests("Innovation", R.drawable.government_innovation, "url", "Government"))
        items.add(Interests("Regional Development", R.drawable.government_health, "url", "Government"))
        items.add(Interests("Social Services", R.drawable.government_defense, "url", "Government"))
        items.add(Interests("Treasury", R.drawable.government_education, "url", "Government"))


        //set data and list adapter
        mAdapter = ChooseInterestsAdapter(items)
        recyclerView!!.adapter = mAdapter

        // on item list clicked
        mAdapter!!.setOnItemClickListener(object : ChooseInterestsAdapter.OnItemClickListener {
            override fun onItemClick(view: View, obj: Int?, position: Int) {
                Log.d("ChooseInterests", "Clicked item at $position")
            }
        })
    }

    private fun goToHome() {
        val onBoardingActivity: OnBoardingActivity = activity as OnBoardingActivity
        onBoardingActivity.goToHome()
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeActivity()
    }
}
