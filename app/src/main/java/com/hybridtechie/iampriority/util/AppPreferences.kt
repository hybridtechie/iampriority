package com.hybridtechie.iampriority.util

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by User on 31-05-2019..
 */
object AppPreferences {
    private const val NAME = "AlertsApp"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    // list of app specific preferences
    private val PREF_IS_FIRST_RUN = Pair("PREF_IS_FIRST_RUN", false)
    private val PREF_USER_NAME = Pair("PREF_USER_NAME", null)
    private val PREF_USER_PROFILE_IMAGE = Pair("PREF_USER_PROFILE_IMAGE", null)
    private val PREF_USER_EMAIL = Pair("PREF_USER_EMAIL", null)
    private val PREF_USER_COUNTRY = Pair("PREF_USER_COUNTRY", null)
    private val PREF_USER_STATE = Pair("PREF_USER_STATE", null)
    private val PREF_USER_DISTRICT = Pair("PREF_USER_DISTRICT", null)

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    /**
     * SharedPreferences extension function, so we won't need to call edit() and apply()
     * ourselves on every SharedPreferences operation.
     */
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var firstRun: Boolean
        get() = preferences.getBoolean(PREF_IS_FIRST_RUN.first, PREF_IS_FIRST_RUN.second)
        set(value) = preferences.edit {
            it.putBoolean(PREF_IS_FIRST_RUN.first, value)
        }

    var userName: String?
        get() = preferences.getString(PREF_USER_NAME.first, PREF_USER_NAME.second)
        set(value) = preferences.edit {
            it.putString(PREF_USER_NAME.first, value)
        }

    var userProfileImage: String?
        get() = preferences.getString(PREF_USER_PROFILE_IMAGE.first, PREF_USER_NAME.second)
        set(value) = preferences.edit {
            it.putString(PREF_USER_PROFILE_IMAGE.first, value)
        }

    var userEmail: String?
        get() = preferences.getString(PREF_USER_EMAIL.first, PREF_USER_NAME.second)
        set(value) = preferences.edit {
            it.putString(PREF_USER_EMAIL.first, value)
        }

    var userCountry: String?
        get() = preferences.getString(PREF_USER_COUNTRY.first, PREF_USER_NAME.second)
        set(value) = preferences.edit {
            it.putString(PREF_USER_COUNTRY.first, value)
        }

    var userState: String?
        get() = preferences.getString(PREF_USER_STATE.first, PREF_USER_NAME.second)
        set(value) = preferences.edit {
            it.putString(PREF_USER_STATE.first, value)
        }

    var userDistrict: String?
        get() = preferences.getString(PREF_USER_DISTRICT.first, PREF_USER_NAME.second)
        set(value) = preferences.edit {
            it.putString(PREF_USER_DISTRICT.first, value)
        }
}