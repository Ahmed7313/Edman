package com.daman.edman.screens.Profile.userProfile

import android.net.Uri
import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aramex.mypos.Common.showErrorMsg
import com.aramex.mypos.Common.showSuccessMsg
import com.aramex.mypos.Data.remote.DataWrapper.Resource
import com.aramex.mypos.Data.remote.DataWrapper.ResponseState
import com.daman.edman.EdmanApp
import com.daman.edman.domain.UseCases.Home.UpdateProfileUseCase
import com.trend.thecontent.data.local.preference.SavePreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val preferences: SavePreferences,
    private val updateProfileUseCase: UpdateProfileUseCase
) : ViewModel() {

    private val TAG = "UserProfileViewModel"
    private val context = EdmanApp.appContext

    // ── Persisted user — pre-fill form fields ─────────────────────────────────
    val savedUser = mutableStateOf(preferences.getUser())

    // ── Edit-mode toggle ──────────────────────────────────────────────────────
    val isEditMode = mutableStateOf(false)

    // ── UI state ──────────────────────────────────────────────────────────────
    private val _state = mutableStateOf(ResponseState())
    val state: State<ResponseState> = _state

    val msg = mutableStateOf("")

    private val _isLoadingProgressBar = MutableSharedFlow<Boolean>()
    val isLoadingProgressBar = _isLoadingProgressBar.asSharedFlow()

    // ── Selected image URI (from gallery) ─────────────────────────────────────
    val selectedImageUri = mutableStateOf<Uri?>(null)

    // ─────────────────────────────────────────────────────────────────────────
    // Public API
    // ─────────────────────────────────────────────────────────────────────────

    fun updateProfile(model: UpdateProfileModel) {
        val body = buildRequestBody(context, model, selectedImageUri.value)
        updateProfileUseCase(body).onEach { response ->
            when (response) {
                is Resource.Loading -> {
                    Timber.tag(TAG).d("updateProfile: loading")
                    _state.value = ResponseState(isLoading = true)
                    _isLoadingProgressBar.emit(true)
                }

                is Resource.Success -> {
                    Timber.tag(TAG).d("updateProfile: success")
                    _state.value = ResponseState(isSuccess = response.data?.status ?: false)
                    msg.value = response.data?.msg ?: "تم تحديث الملف الشخصي بنجاح"
                    _isLoadingProgressBar.emit(false)

                    // Persist the updated user locally
                    response.data?.data?.let { updatedUser ->
                        preferences.putUser(updatedUser)
                        savedUser.value = updatedUser
                    }

                    context.showSuccessMsg(msg.value)
                    isEditMode.value = false
                }

                is Resource.Error -> {
                    Timber.tag(TAG).e("updateProfile: error – ${response.message}")
                    _state.value = ResponseState(isError = response.message ?: "An error occurred")
                    msg.value = response.message ?: "An error occurred"
                    _isLoadingProgressBar.emit(false)
                    context.showErrorMsg(msg.value)
                }
            }
        }.launchIn(viewModelScope)
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Model
    // ─────────────────────────────────────────────────────────────────────────

    data class UpdateProfileModel(
        val name: String,
        val email: String,
        val phone: String,
        val idNumber: String,
    )

    // ─────────────────────────────────────────────────────────────────────────
    // Helpers
    // ─────────────────────────────────────────────────────────────────────────

    private fun buildRequestBody(
        context: Context,
        model: UpdateProfileModel,
        imageUri: Uri?
    ): RequestBody {
        val builder = MultipartBody.Builder().setType(MultipartBody.FORM)

        builder.addFormDataPart("name",     model.name)
        builder.addFormDataPart("email",    model.email)
        builder.addFormDataPart("phone",    model.phone)
        builder.addFormDataPart("idNumber", model.idNumber)

        // Attach image only when the user actually picked one
        imageUri?.let { uri ->
            val file = uriToFile(context, uri)
            if (file != null && file.exists()) {
                builder.addFormDataPart(
                    name     = "image",
                    filename = file.name,
                    body     = file.asRequestBody("image/*".toMediaTypeOrNull())
                )
            }
        }

        return builder.build()
    }

    /**
     * Copies the content URI into a temporary file so OkHttp can read it.
     * This is the standard approach for file uploads from gallery/camera in Android.
     */
    private fun uriToFile(context: Context, uri: Uri): File? {
        return try {
            val inputStream = context.contentResolver.openInputStream(uri) ?: return null
            val tempFile = File(context.cacheDir, "profile_image_${System.currentTimeMillis()}.jpg")
            FileOutputStream(tempFile).use { out -> inputStream.copyTo(out) }
            tempFile
        } catch (e: Exception) {
            Timber.tag(TAG).e(e, "uriToFile failed")
            null
        }
    }
}
