package com.daman.edman.screens.Home.GarantieRequest

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aramex.mypos.Common.showErrorMsg
import com.aramex.mypos.Common.showSuccessMsg
import com.aramex.mypos.Data.remote.DataWrapper.Resource
import com.daman.edman.domain.UseCases.Home.CreateOrder.CreateOrderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

@HiltViewModel
class CreateRequestViewModel @Inject constructor(
    private val createOrderUseCase: CreateOrderUseCase,
    @ApplicationContext private val appContext: Context
) : ViewModel() {

    private val _state = MutableStateFlow(CreateOrderState())
    val state: StateFlow<CreateOrderState> = _state.asStateFlow()

    private val _isLoading = MutableSharedFlow<Boolean>()
    val isLoading = _isLoading.asSharedFlow()

    private val _navigateNext = MutableSharedFlow<Unit>()
    val navigateNext = _navigateNext.asSharedFlow()

    fun createOrder(
        sellerId: Int,
        productDescription: String,
        productStatus: Int,
        deliveryTime: String,
        price: String,
        orderNumber: String,
        productOrderUrl: String,
        officialPageUrl: String,
        productImageUri: Uri?,
        billOfLadingImageUri: Uri?,
        receiptBillImageUri: Uri?,
        shippingConfirmed: Boolean
    ) {
        // 1) local validation
        val newState = CreateOrderState(
            productDescriptionError   = productDescription.isBlank(),
            orderNumberError         = orderNumber.isBlank(),
            priceError               = price.isBlank(),
            productStatusError       = productStatus !in listOf(0,1),
            deliveryTimeError        = deliveryTime.isBlank(),
            productOrderUrlError     = productOrderUrl.isBlank(),
            officialPageUrlError     = officialPageUrl.isBlank(),
            productImageError        = productImageUri == null,
            billOfLadingImageError   = billOfLadingImageUri == null,
            receiptBillImageError    = receiptBillImageUri == null,
            shippingConfirmationError= !shippingConfirmed
        )
        _state.value = newState
        if (newState.hasErrors()) return

        viewModelScope.launch {
            _isLoading.emit(true)

            // 2) build one MultipartBody (which is a RequestBody)
            val builder = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .apply {
                    addFormDataPart("sellerId", sellerId.toString())
                    addFormDataPart("product", productDescription)
                    addFormDataPart("productStatus", productStatus.toString())
                    addFormDataPart("deliveryTime", deliveryTime)
                    addFormDataPart("price", price)
                    addFormDataPart("orderNumber", orderNumber)
                    addFormDataPart("productOrderUrl", productOrderUrl)
                    addFormDataPart("officialPageUrl", officialPageUrl)

                    productImageUri?.let { uri ->
                        uriToPart("productImage", uri)?.let(::addPart)
                    }
                    billOfLadingImageUri?.let { uri ->
                        uriToPart("billOfLadingImage", uri)?.let(::addPart)
                    }
                    receiptBillImageUri?.let { uri ->
                        uriToPart("receiptBillImage", uri)?.let(::addPart)
                    }
                }

            val requestBody: RequestBody = builder.build()

            // 3) call use-case
            createOrderUseCase(requestBody).collect { resource ->
                when (resource) {
                    is Resource.Loading -> { /* keep loader */ }
                    is Resource.Success -> {
                        _isLoading.emit(false)
                        _state.update { it.copy(isSuccess = true) }
                        _navigateNext.emit(Unit)
                        appContext.showSuccessMsg("تم إنشاء الطلب بنجاح")
                    }
                    is Resource.Error -> {
                        _isLoading.emit(false)
                        _state.update { it.copy(errorMessage = resource.message) }
                        appContext.showErrorMsg(resource.message ?: "خطأ في الإنشاء")
                    }
                }
            }
        }
    }

    private suspend fun uriToPart(fieldName: String, uri: Uri): MultipartBody.Part? {
        val resolver = appContext.contentResolver
        val stream   = resolver.openInputStream(uri) ?: return null
        val bytes    = stream.readBytes()
        val mime     = resolver.getType(uri) ?: "image/*"
        val rb       = bytes.toRequestBody(mime.toMediaType())
        val filename = uri.lastPathSegment ?: "$fieldName.jpg"
        return MultipartBody.Part.createFormData(fieldName, filename, rb)
    }
}

// move the helper into the same file:
data class CreateOrderState(
    val productDescriptionError: Boolean = false,
    val orderNumberError:         Boolean = false,
    val priceError:               Boolean = false,
    val productStatusError:       Boolean = false,
    val deliveryTimeError:        Boolean = false,
    val productOrderUrlError:     Boolean = false,
    val officialPageUrlError:     Boolean = false,
    val productImageError:        Boolean = false,
    val billOfLadingImageError:   Boolean = false,
    val receiptBillImageError:    Boolean = false,
    val shippingConfirmationError:Boolean = false,
    val isSuccess:                Boolean = false,
    val errorMessage:             String?  = null
) {
    fun hasErrors() = listOf(
        productDescriptionError,
        orderNumberError,
        priceError,
        productStatusError,
        deliveryTimeError,
        productOrderUrlError,
        officialPageUrlError,
        productImageError,
        billOfLadingImageError,
        receiptBillImageError,
        shippingConfirmationError
    ).any { it }
}
