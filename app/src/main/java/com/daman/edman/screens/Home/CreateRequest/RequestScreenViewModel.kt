package com.daman.edman.screens.Home.CreateRequest

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.trend.thecontent.data.local.preference.SavePreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RequestViewModel  @Inject constructor(
    private val preferences: SavePreferences,
) : ViewModel() {

    val user = mutableStateOf(preferences.getUser())

}