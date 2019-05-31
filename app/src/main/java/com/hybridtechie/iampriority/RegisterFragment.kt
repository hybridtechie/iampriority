package com.hybridtechie.iampriority

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText

class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        val countryChooser = view.findViewById<TextInputEditText>(R.id.country_chooser)
        countryChooser.setOnClickListener { v -> showCountryDialog(v) }

        val stateChooser = view.findViewById<TextInputEditText>(R.id.state_chooser)
        stateChooser.setOnClickListener { v -> showStateDialog(v) }

        val districtChooser = view.findViewById<TextInputEditText>(R.id.district_chooser)
        districtChooser.setOnClickListener { v -> showDistrictDialog(v) }

        return view
    }

    private fun showCountryDialog(v: View) {
        val array = arrayOf("United State", "Germany", "United Kingdom", "Australia")
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Country")
        builder.setSingleChoiceItems(array, -1) { dialogInterface, i ->
            (v as TextInputEditText).setText(array[i])
            dialogInterface.dismiss()
        }
        builder.show()
    }

    private fun showStateDialog(v: View) {
        val array = arrayOf("Kerala", "Tamil Nadu", "Andra Pradesh", "Karnataka")
        val builder = AlertDialog.Builder(context)
        builder.setTitle("State")
        builder.setSingleChoiceItems(array, -1) { dialogInterface, i ->
            (v as TextInputEditText).setText(array[i])
            dialogInterface.dismiss()
        }
        builder.show()
    }

    private fun showDistrictDialog(v: View) {
        val array = arrayOf("Kottayam", "Thiruvananthapuram", "Kannur", "Pathanamthitta")
        val builder = AlertDialog.Builder(context)
        builder.setTitle("District")
        builder.setSingleChoiceItems(array, -1) { dialogInterface, i ->
            (v as TextInputEditText).setText(array[i])
            dialogInterface.dismiss()
        }
        builder.show()
    }

    companion object {
        @JvmStatic
        fun newInstance() = RegisterFragment()
    }
}
