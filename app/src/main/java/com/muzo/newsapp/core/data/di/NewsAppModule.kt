package com.muzo.newsapp.core.data.di

import com.muzo.newsapp.core.data.local.repository.LocalNewsRepo
import com.muzo.newsapp.core.data.local.repository.LocalNewsRepoImpl
import com.muzo.newsapp.core.data.local.source.LocalNewsDataSource
import com.muzo.newsapp.core.data.local.source.LocalNewsDataSourceImpl
import com.muzo.newsapp.core.data.remote.pagination.NewsPaginationRepository
import com.muzo.newsapp.core.data.remote.pagination.NewsPaginationRepositoryImpl
import com.muzo.newsapp.core.data.remote.repository.NewsRemoteRepository
import com.muzo.newsapp.core.data.remote.repository.NewsRemoteRepositoryImpl
import com.muzo.newsapp.core.data.remote.repository.auth.AuthRepository
import com.muzo.newsapp.core.data.remote.repository.auth.AuthRepositoryImpl
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

    @Binds
    fun bindLocalRepository(
        newsLocalNewsRepoImpl: LocalNewsRepoImpl
    ): LocalNewsRepo

    @Binds
    fun bindNewsLocalDataSource(
        sourceImpl: LocalNewsDataSourceImpl
    ): LocalNewsDataSource


    @Binds
    fun bindPaginationRepository(
        pagingRepositoryImpl: NewsPaginationRepositoryImpl
    ): NewsPaginationRepository


    @Binds
    fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository


}