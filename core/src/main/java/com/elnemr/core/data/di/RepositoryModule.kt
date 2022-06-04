package com.elnemr.core.data.di

import com.elnemr.core.data.repository.RepositoryImpl
import com.elnemr.core.data.repository.datasource.RemoteDataSource
import com.elnemr.core.data.repository.datasource.RemoteDataSourceImpl
import com.elnemr.core.domain.repository.IRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(repository: RepositoryImpl): IRepository =
        repository

    @Provides
    @Singleton
    fun provideRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource =
        remoteDataSourceImpl

}