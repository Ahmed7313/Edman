package com.daman.edman.screens.Auth.Login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aramex.mypos.Data.remote.DataWrapper.Resource
import com.aramex.mypos.Data.remote.DataWrapper.ResponseState
import com.daman.edman.domain.UseCases.Auth.LoginUseCase
import com.trend.thecontent.data.local.preference.PreferencesConstants.FINGER_PRINT
import com.trend.thecontent.data.local.preference.SavePreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import okhttp3.MultipartBody
import okhttp3.RequestBody
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: LoginUseCase,
    val preferences: SavePreferences,
) : ViewModel() {

    private val _state = mutableStateOf(ResponseState())
    val state: State<ResponseState> = _state
    val msg = mutableStateOf("")

    private val _stateFingerPrint = mutableStateOf(ResponseState())
    val stateFingerPrint: State<ResponseState> = _stateFingerPrint


    val _navigateHome = MutableSharedFlow<Boolean>()
    val navigateHome = _navigateHome.asSharedFlow()

    val _navigate = MutableSharedFlow<Boolean>()
    val navigate = _navigate.asSharedFlow()

    private val _isLoadingProgressBar = MutableSharedFlow<Boolean>()
    val isLoadingProgressBar = _isLoadingProgressBar.asSharedFlow()

    private val TAG = "LoginViewModel"

    fun login(model: LoginModel) {
        useCase(createRequestBody(model)).onEach { response ->
            when (response) {
                is Resource.Loading -> {
                    Timber.tag(TAG).d("getIntro: loading")
                    _isLoadingProgressBar.emit(true)
                }

                is Resource.Success -> {
                    Timber.tag(TAG).d("getIntro: response")
                    _state.value = ResponseState(isSuccess = response.data?.status ?: false)
                    msg.value = response.data?.msg ?: "An error occurred"
                    Timber.tag(TAG).i("Error message set: ${msg.value}")
                    Timber.tag(TAG).i("Error message set: ${response.data?.msg}")
                    _isLoadingProgressBar.emit(false)
                    if (response.data?.status == true)
                        _navigate.emit(true)
                }

                is Resource.Error -> {
                    Timber.tag(TAG).d("getIntro: error")
                    _isLoadingProgressBar.emit(false)
                    Timber.tag(TAG).i(response.message)
                    msg.value = response.data?.msg ?: "An error occurred"
                    Timber.tag(TAG).i("Error message set: ${msg.value}")

                }
            }
        }.launchIn(viewModelScope)
    }

    data class LoginModel(
        val phone: String,
    )


    private fun createRequestBody(model: LoginModel): RequestBody {
        val multipartBuilder = MultipartBody.Builder().setType(MultipartBody.FORM)

        // Required fields
        multipartBuilder.addFormDataPart("phone", model.phone)
        multipartBuilder.addFormDataPart("device", "android")
        return multipartBuilder.build()
    }

    fun clearErrorMessage() {
        msg.value = ""
    }


}