package com.muzo.newsapp.core.data.di

import com.muzo.newsapp.core.data.remote.repository.NewsRemoteRepository
import com.muzo.newsapp.core.data.remote.repository.NewsRemoteRepositoryImpl
import com.muzo.newsapp.core.data.remote.source.NewsRemoteDataSource
import com.muzo.newsapp.core.data.remote.source.NewsRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface NewsAppModule {

    @Binds
    fun bindNewsRemoteDataSource(
        sourceImpl: NewsRemoteDataSourceImpl
    ): NewsRemoteDataSource

    @Binds
    fun bindNewsRemoteRepository(
        newsRemoteRepositoryImpl: NewsRemoteRepositoryImpl
    ): NewsRemoteRepository

}