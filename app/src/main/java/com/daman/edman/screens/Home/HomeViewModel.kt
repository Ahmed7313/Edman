package com.daman.edman.screens.Home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aramex.mypos.Common.showErrorMsg
import com.aramex.mypos.Common.showSuccessMsg
import com.aramex.mypos.Data.remote.DataWrapper.Resource
import com.aramex.mypos.Data.remote.DataWrapper.ResponseState
import com.daman.edman.EdmanApp
import com.daman.edman.data.remote.DTO.ChangeUserInfo.CompleteUserResponse
import com.daman.edman.domain.UseCases.Home.CompleteUserUseCase
import com.daman.edman.screens.Auth.Login.LoginViewModel.LoginModel
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
class HomeViewModel @Inject constructor(
    private val preferences: SavePreferences,
    private val completeUserUseCase: CompleteUserUseCase
) : ViewModel() {

    val user = mutableStateOf(preferences.getUser())


    private val _state = mutableStateOf(ResponseState())
    val state: State<ResponseState> = _state
    val msg = mutableStateOf("")

    private val _stateFingerPrint = mutableStateOf(ResponseState())
    val stateFingerPrint: State<ResponseState> = _stateFingerPrint


    val context = EdmanApp.appContext

    val _navigate = MutableSharedFlow<Boolean>()
    val navigate = _navigate.asSharedFlow()

    private val _isLoadingProgressBar = MutableSharedFlow<Boolean>()
    val isLoadingProgressBar = _isLoadingProgressBar.asSharedFlow()

    private val TAG = "LoginViewModel"

    fun completeUserData(model: CompleteUserModel) {
        completeUserUseCase(createRequestBody(model)).onEach { response ->
            when (response) {
                is Resource.Loading -> {
                    Timber.tag(TAG).d("complete data: loading")
                    _isLoadingProgressBar.emit(true)
                }

                is Resource.Success -> {
                    Timber.tag(TAG).d("complete data: response")
                    _state.value = ResponseState(isSuccess = response.data?.status ?: false)
                    msg.value = response.data?.msg ?: "An error occurred"
                    Timber.tag(TAG).i("Error message set: ${msg.value}")
                    Timber.tag(TAG).i("Error message set: ${response.data?.msg}")
                    _isLoadingProgressBar.emit(false)

                    response.data?.data?.let { preferences.putUser(it) }
                    context.showSuccessMsg(msg.value)
                }

                is Resource.Error -> {
                    Timber.tag(TAG).d("complete data: error")
                    _isLoadingProgressBar.emit(false)
                    Timber.tag(TAG).i(response.message)
                    msg.value = response.data?.msg ?: "An error occurred"
                    context.showErrorMsg(msg.value)
                    Timber.tag(TAG).i("Error message set: ${msg.value}")

                }
            }
        }.launchIn(viewModelScope)
    }

    data class CompleteUserModel(
        val name : String,
        val email : String,
        val idNumber : String,
        val device : String,
        val phone : String? = null
    )


    private fun createRequestBody(model: CompleteUserModel): RequestBody {
        val multipartBuilder = MultipartBody.Builder().setType(MultipartBody.FORM)

        // Required fields
        multipartBuilder.addFormDataPart("name", model.name)
        multipartBuilder.addFormDataPart("email", model.email)
        multipartBuilder.addFormDataPart("idNumber", model.idNumber)
        multipartBuilder.addFormDataPart("device", model.device)
        //multipartBuilder.addFormDataPart("phone", model.phone ?: user.value.phone)
        return multipartBuilder.build()
    }

    fun userFinishedOnBoarding(): Boolean {
        val user = preferences.getUser()

        return user.isProfileCompleted ?: false
    }
}