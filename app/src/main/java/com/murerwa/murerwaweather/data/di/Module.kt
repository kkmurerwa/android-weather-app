package com.murerwa.murerwaweather.data.di

import com.google.gson.GsonBuilder
import com.murerwa.murerwaweather.data.network.ApiClient
import com.murerwa.murerwaweather.data.network.Constants
import com.murerwa.murerwaweather.data.repository.WeatherRepositoryImpl
import com.murerwa.murerwaweather.domain.repository.WeatherRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private val repositoryModules: Module = module {
    single <WeatherRepository>{ WeatherRepositoryImpl(get()) }
}

private val networkingModules: Module = module {
    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = when (BuildConfig.BUILD_TYPE) {
            "release" -> HttpLoggingInterceptor.Level.NONE
            else -> HttpLoggingInterceptor.Level.BODY
        }

        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    single<Retrofit> {
        val gson = GsonBuilder()
            .serializeNulls()
            .create()

        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(get())
            .build()
    }
}

val apiModules: Module = module {
    single<ApiClient> { get<Retrofit>().create(ApiClient::class.java) }
}

val dataModules: List<Module> = listOf(
    repositoryModules,
    networkingModules,
    apiModules
)
