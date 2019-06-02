package com.hybridtechie.iampriority

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class OnBoardingActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_on_boarding)
        supportActionBar?.hide()

        if (savedInstanceState == null) {
            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.flContent, RegisterFragment.newInstance())
            transaction.commit()
        }
    }

    fun goToChooseInterests() {
        val newGamefragment = ChooseInterestsFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.flContent, newGamefragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }


    companion object
}
