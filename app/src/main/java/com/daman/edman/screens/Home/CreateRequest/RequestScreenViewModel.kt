package com.daman.edman.screens.Home.CreateRequest

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aramex.mypos.Common.showErrorMsg
import com.aramex.mypos.Common.showSuccessMsg
import com.aramex.mypos.Data.remote.DataWrapper.Resource
import com.aramex.mypos.Data.remote.DataWrapper.ResponseState
import com.daman.edman.EdmanApp
import com.daman.edman.data.remote.DTO.SearchDTO.Data
import com.daman.edman.domain.UseCases.RequestUseCases.SearchUserUseCase
import com.trend.thecontent.data.local.preference.SavePreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RequestViewModel @Inject constructor(
    private val preferences: SavePreferences,
    private val searchUserUseCase: SearchUserUseCase
) : ViewModel() {

    val user = mutableStateOf(preferences.getUser())
    
    private val _searchResult = MutableStateFlow<Data?>(null)
    val searchResult: StateFlow<Data?> = _searchResult

    private val _state = mutableStateOf(ResponseState())
    val state: State<ResponseState> = _state

    private val _isLoadingProgressBar = MutableSharedFlow<Boolean>()
    val isLoadingProgressBar = _isLoadingProgressBar.asSharedFlow()

    private val _navigate = MutableSharedFlow<Boolean>()
    val navigate = _navigate.asSharedFlow()

    private val TAG = "RequestViewModel"

    fun searchUser(phone: String) {
        searchUserUseCase(phone).onEach { response ->
            when (response) {
                is Resource.Loading -> {
                    Timber.tag(TAG).d("search user: loading")
                    _isLoadingProgressBar.emit(true)
                }
                is Resource.Success -> {
                    Timber.tag(TAG).d("search user: success")
                    _state.value = ResponseState(isSuccess = response.data?.status ?: false)
                    _isLoadingProgressBar.emit(false)
                    response.data?.data?.let { userData ->
                        _searchResult.value = userData
                        EdmanApp.appContext.showSuccessMsg("User found successfully")
                    }
                }
                is Resource.Error -> {
                    Timber.tag(TAG).d("search user: error")
                    _isLoadingProgressBar.emit(false)
                    Timber.tag(TAG).i(response.message)
                    EdmanApp.appContext.showErrorMsg(response.message ?: "Failed to search user")
                }
            }
        }.launchIn(viewModelScope)
    }
}