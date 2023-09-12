package com.muzo.newsapp.core.data.di

import com.muzo.newsapp.core.constants.Constants.Companion.BASE_URL
import com.muzo.newsapp.core.data.remote.api.ResultService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient,gsonConverterFactory: GsonConverterFactory):Retrofit{
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(gsonConverterFactory)
            .client(okHttpClient).build()
    }

    @Provides
    @Singleton
    fun provideHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ):OkHttpClient{
        return  OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor():HttpLoggingInterceptor=HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideGsonConvertorFactory():GsonConverterFactory{
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideNewsAppService(retrofit: Retrofit):ResultService{
        return retrofit.create(ResultService::class.java)
    }



}