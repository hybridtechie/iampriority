package com.hybridtechie.iampriority

import android.app.Application
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.hybridtechie.iampriority.util.AppPreferences


/**
 * Created by HyBr!dT3cH!3 on 17/05/2019..
 */
class IAmPriorityApplication : Application() {

    private lateinit var mFirebaseAnalytics: FirebaseAnalytics

    override fun onCreate() {
        super.onCreate()
        AppPreferences.init(this)
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
    }

    fun uploadEventToAnlytics(itemId: String, name: String, type: String) {
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, itemId)
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name)
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, type)
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
    }
}