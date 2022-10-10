package com.murerwa.murerwaweather.presentation.utils

import com.murerwa.murerwaweather.data.network.NetworkResult
import com.murerwa.murerwaweather.data.network.readError
import timber.log.Timber

fun <T> convertToUIState(response: NetworkResult<T>): UIState<T> {
    return when (response) {
        is NetworkResult.Success -> {
            UIState.Success(response.value)
        }
        is NetworkResult.Failure -> {
            if (response.isNetworkError) {
                UIState.Error(errorMessage = "Network error", isNetworkError = true)
            } else {
                if (response.errorBody != null) {
                    Timber.d("Response is Not Null")
                    val error = response.errorBody.readError()
                    if (!error.isNullOrEmpty()) {
                        UIState.Error(error)
                    } else {
                        UIState.Error("Error processing your request")
                    }

                } else {
                    Timber.d("Response is Null")
                    UIState.Error("Error processing your request")
                }
            }
        }
    }
}