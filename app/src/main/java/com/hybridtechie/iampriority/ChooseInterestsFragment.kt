package com.hybridtechie.iampriority

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class ChooseInterestsFragment : Fragment() {

    private var parent_view: View? = null

    private var recyclerView: RecyclerView? = null
    private var mAdapter: AdapterChooseInterests? = null

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
        recyclerView = view.findViewById(R.id.recyclerView) as RecyclerView
        recyclerView!!.layoutManager = GridLayoutManager(context, 3)
        recyclerView!!.setHasFixedSize(true)

        var items = ArrayList<Interests>()
        items.add(Interests("Gears of War", R.drawable.photo_male_2, "url", "Government"))
        items.add(Interests("Gears of War", R.drawable.ic_expand_arrow, "url", "Government"))
        items.add(Interests("Gears of War", R.drawable.photo_male_2, "url", "Government"))
        items.add(Interests("Gears of War", R.drawable.ic_expand_arrow, "url", "Government"))
        items.add(Interests("Gears of War", R.drawable.photo_male_2, "url", "Government"))
        items.add(Interests("Gears of War", R.drawable.ic_expand_arrow, "url", "Government"))
        items.add(Interests("Gears of War", R.drawable.photo_male_2, "url", "Government"))
        items.add(Interests("Gears of War", R.drawable.ic_expand_arrow, "url", "Government"))

        //set data and list adapter
        mAdapter = AdapterChooseInterests(items)
        recyclerView!!.adapter = mAdapter

        // on item list clicked
        mAdapter!!.setOnItemClickListener(object : AdapterChooseInterests.OnItemClickListener {
            override fun onItemClick(view: View, obj: Int?, position: Int) {
                Log.d("ChooseInterests", "Clicked item at $position")
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = ChooseInterestsFragment()
    }
}
