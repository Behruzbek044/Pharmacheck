package com.example.pharmachek.utils

import android.content.Context
import androidx.core.content.edit

class SharedPrefHelper(context: Context) {

    private val sharedPreferences = context.getSharedPreferences(Constants.SHARED_PREFERENCE, Context.MODE_PRIVATE)

    var hasOnboardingScreenOpened: Boolean
        get() = sharedPreferences.getBoolean(Constants.ON_BOARDING_SCREEN_OPENED_KEY, false)
        set(value) = sharedPreferences.edit {
            putBoolean(Constants.ON_BOARDING_SCREEN_OPENED_KEY, value)
            apply()
        }
}