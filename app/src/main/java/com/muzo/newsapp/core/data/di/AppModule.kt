package com.muzo.newsapp.core.data.di

import android.content.Context
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.muzo.newsapp.core.constants.Constants.Companion.BASE_URL
import com.muzo.newsapp.core.constants.Constants.Companion.DATABASE_NAME
import com.muzo.newsapp.core.data.local.room.NewsDataBase
import com.muzo.newsapp.core.data.remote.api.ResultService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    @Singleton
    @Provides
    fun provideRunDao(db:NewsDataBase)=db.getNewsDao()

    @Provides
    @Singleton
    fun provideNewsDataBase(@ApplicationContext app : Context)=
        Room.databaseBuilder(app,NewsDataBase::class.java, DATABASE_NAME).build()

    @Provides
    @Singleton
    fun providesFirebaseApp(): FirebaseAuth {
        return FirebaseAuth .getInstance()
    }





}