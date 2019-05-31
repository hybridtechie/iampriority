package com.hybridtechie.iampriority

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

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

        parent_view = view.findViewById(android.R.id.content)


        initComponent(view)

        return view
    }

    private fun initComponent(view: View) {
        recyclerView = view.findViewById(R.id.recyclerView) as RecyclerView
        recyclerView!!.layoutManager = GridLayoutManager(context, 3)
        recyclerView!!.setHasFixedSize(true)

        var items: List<Int>
        // items = { 1,2,3 }

        //set data and list adapter
        // mAdapter = AdapterChooseInterests(items)
        // recyclerView.setAdapter(mAdapter)

        // on item list clicked
        mAdapter!!.setOnItemClickListener(object : AdapterChooseInterests.OnItemClickListener {
            override fun onItemClick(view: View, obj: Int?, position: Int) {
                // Snackbar.make(parent_view, "Item $position clicked", Snackbar.LENGTH_SHORT).show()
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = RegisterFragment()
    }
}
