package com.josele.lovemusic.di

import com.google.gson.GsonBuilder
import com.josele.lovemusic.BuildConfig
import com.josele.lovemusic.data.remote.api.LoveMusicApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    val FORMAT = "format"
    val BODY_FORMAT = "json"
    val NAME_API = "api_key"
    val API_KEY = BuildConfig.API_KEY
    val BASE_URL = "https://ws.audioscrobbler.com/2.0/"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {

        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor { chain ->
            val original: Request = chain.request()
            val originalHttpUrl: HttpUrl = original.url

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter(NAME_API, API_KEY)
                .addQueryParameter(FORMAT, BODY_FORMAT)
                .build()

            val requestBuilder: Request.Builder = original.newBuilder()
                .url(url)
            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }

        return Retrofit.Builder()
            .client(httpClient.build())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().create()))
            .build()
    }

    @Singleton
    @Provides
    fun provideLoveMusicApiClient(retrofit: Retrofit): LoveMusicApiClient {
        return retrofit.create(LoveMusicApiClient::class.java)
    }

}