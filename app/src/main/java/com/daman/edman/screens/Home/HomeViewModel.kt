package com.daman.edman.screens.Home

import androidx.lifecycle.ViewModel
import com.trend.thecontent.data.local.preference.SavePreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val preferences: SavePreferences
) : ViewModel() {


    val user = preferences.getUser()

    fun userFinishedOnBoarding(): Boolean {
        val user = preferences.getUser()

        return user.isProfileCompleted ?: false
    }
}