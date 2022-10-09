package com.murerwa.murerwaweather.domain.models

data class BaseResponse<T>(
    val cod: String,
    val message: Int,
    val cnt: Int,
    val data: T
)