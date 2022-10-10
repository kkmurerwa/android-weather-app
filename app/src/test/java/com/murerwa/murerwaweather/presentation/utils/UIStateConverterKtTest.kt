package com.murerwa.murerwaweather.presentation.utils

import com.murerwa.murerwaweather.data.network.NetworkResult
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*

import org.junit.Test
import timber.log.Timber

class UIStateConverterKtTest {

    @Test
    fun testConvertToUIState() {
        assertTrue(convertToUIState(NetworkResult.Success("Success")) is UIState.Success)
        assertTrue(convertToUIState(NetworkResult.Failure(true, null, null)) is UIState.Error)
        assertTrue(convertToUIState(NetworkResult.Failure(false, null, null)) is UIState.Error)
    }

    @Test
    fun testErrorMessagesConvertToUiState() {
        val error1 = UIState.Error("Error processing your request")
        assertTrue(convertToUIState(NetworkResult.Failure(false, null, null)) == error1)

        val error2 = UIState.Error(errorMessage = "Network error", isNetworkError = true)
        assertTrue(convertToUIState(NetworkResult.Failure(true, null, null)) == error2)

        val error3 = UIState.Error("Error processing your request")
        assertTrue(convertToUIState(NetworkResult.Failure(false, null, null)) == error3)
    }
}