package com.hybridtechie.iampriority.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.hybridtechie.iampriority.R
import com.hybridtechie.iampriority.adapter.MyFragmentPagerAdapter
import com.hybridtechie.iampriority.fragments.MyFrament


class OnBoardingActivity : AppCompatActivity() {

    private lateinit var viewpager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        initViews()

        setupViewPager()
    }

    private fun initViews() {
        viewpager = findViewById(R.id.viewpager)
    }

    private fun setupViewPager() {

        val adapter = MyFragmentPagerAdapter(supportFragmentManager)

        val firstFragment: MyFrament = MyFrament.newInstance("First Fragment")
        val secondFragment: MyFrament = MyFrament.newInstance("Second Fragment")
        val thirdFragment = MyFrament.newInstance("Third Fragment")

        adapter.addFragment(firstFragment, "ONE")
        adapter.addFragment(secondFragment, "TWO")
        adapter.addFragment(thirdFragment, "THREE")

        viewpager.adapter = adapter


    }
}
