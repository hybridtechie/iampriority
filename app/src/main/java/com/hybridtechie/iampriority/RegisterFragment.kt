package com.hybridtechie.iampriority

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.hybridtechie.iampriority.util.AppPreferences


class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)


        val welcomeLabel = view.findViewById<TextView>(R.id.google_name)
        //welcomeLabel.text = ("Welcome  " + AppPreferences.userName)

        val userName = view.findViewById<TextView>(R.id.input_name)
        userName.text = AppPreferences.userName

        val userEmail = view.findViewById<TextView>(R.id.input_email)
        userEmail.text = AppPreferences.userEmail

        val countryChooser = view.findViewById<TextInputEditText>(R.id.country_chooser)
        countryChooser.setOnClickListener { v -> showCountryDialog(v) }

        val stateChooser = view.findViewById<TextInputEditText>(R.id.state_chooser)
        stateChooser.setOnClickListener { v -> showStateDialog(v) }

        val districtChooser = view.findViewById<TextInputEditText>(R.id.district_chooser)
        districtChooser.setOnClickListener { v -> showDistrictDialog(v) }

        val btnNext = view.findViewById<MaterialButton>(R.id.btn_next)
        btnNext.setOnClickListener { goToChooseInterests() }

        return view
    }

    private fun showCountryDialog(v: View) {
        val array = arrayOf("India", "Germany", "United Kingdom", "Australia")
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

    private fun goToChooseInterests() {
        val onBoardingActivity: OnBoardingActivity = activity as OnBoardingActivity
        onBoardingActivity.goToChooseInterests()
    }

    private fun swapFragment() {
        val newGamefragment = ChooseInterestsFragment()
        val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.flContent, newGamefragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    companion object {
        @JvmStatic
        fun newInstance() = RegisterFragment()
    }
}
