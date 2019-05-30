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

        val adapter = MyFragmentPagerAdapter(getSupportFragmentManager())

        var firstFragmet: MyFrament = MyFrament.newInstance("First Fragment")
        var secondFragmet: MyFrament = MyFrament.newInstance("Second Fragment")
        var thirdFragmet: MyFrament = MyFrament.newInstance("Third Fragment")

        adapter.addFragment(firstFragmet, "ONE")
        adapter.addFragment(secondFragmet, "TWO")
        adapter.addFragment(thirdFragmet, "THREE")

        viewpager.adapter = adapter


    }
}
