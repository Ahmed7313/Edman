package com.daman.edman.screens.MyOrders

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aramex.mypos.Data.remote.DataWrapper.Resource
import com.aramex.mypos.Data.remote.DataWrapper.ResponseState
import com.daman.edman.data.remote.DTO.OrderDTO.Data
import com.daman.edman.domain.UseCases.Home.GetAllOrdersUseCase
import com.trend.thecontent.data.local.preference.SavePreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MyOrdersViewModel @Inject constructor(
    private val preferences: SavePreferences,
    private val getAllOrdersUseCase: GetAllOrdersUseCase
) : ViewModel() {

    private val TAG = "MyOrdersViewModel"

    // ── Orders list state ────────────────────────────────────────────────────
    private val _orders = mutableStateOf<List<Data>>(emptyList())
    val orders: State<List<Data>> = _orders

    // ── UI loading / error state ──────────────────────────────────────────────
    private val _state = mutableStateOf(ResponseState())
    val state: State<ResponseState> = _state

    val msg = mutableStateOf("")

    // ── Active status filter (null = all) ────────────────────────────────────
    private val _selectedStatus = mutableStateOf<Int?>(null)
    val selectedStatus: State<Int?> = _selectedStatus

    private val _isLoadingProgressBar = MutableSharedFlow<Boolean>()
    val isLoadingProgressBar = _isLoadingProgressBar.asSharedFlow()

    // ── Auto-fetch on creation ────────────────────────────────────────────────
    init {
        fetchOrders()
    }

    /**
     * Fetch orders from the API.
     * Pass a [status] to filter:
     *   null  → all orders
     *   1     → pending
     *   2     → in-progress
     *   3     → completed
     *   4     → cancelled
     */
    fun fetchOrders(status: Int? = _selectedStatus.value) {
        _selectedStatus.value = status
        getAllOrdersUseCase(status).onEach { response ->
            when (response) {
                is Resource.Loading -> {
                    Timber.tag(TAG).d("getAllOrders: loading")
                    _state.value = ResponseState(isLoading = true)
                    _isLoadingProgressBar.emit(true)
                }

                is Resource.Success -> {
                    Timber.tag(TAG).d("getAllOrders: success – ${response.data?.data?.size} orders")
                    _state.value = ResponseState(isSuccess = response.data?.status ?: false)
                    _orders.value = response.data?.data ?: emptyList()
                    _isLoadingProgressBar.emit(false)
                }

                is Resource.Error -> {
                    Timber.tag(TAG).e("getAllOrders: error – ${response.message}")
                    _state.value = ResponseState(isError = response.message ?: "An error occurred")
                    msg.value = response.message ?: "An error occurred"
                    _isLoadingProgressBar.emit(false)
                }
            }
        }.launchIn(viewModelScope)
    }
}