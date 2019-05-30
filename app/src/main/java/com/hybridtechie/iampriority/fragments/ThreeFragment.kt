package com.hybridtechie.iampriority.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.hybridtechie.iampriority.R


class ThreeFragment : Fragment() {

    fun OneFragment() {

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View? = inflater.inflate(R.layout.fragment_home, container, false);

        var textView: TextView = view!!.findViewById(R.id.text)
        textView!!.text = "THIRD FRAGMENT"

        return view
    }


}