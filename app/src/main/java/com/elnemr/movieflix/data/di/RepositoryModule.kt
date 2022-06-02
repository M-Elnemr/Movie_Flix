package com.elnemr.movieflix.data.di

import com.elnemr.movieflix.data.repository.RepositoryImpl
import com.elnemr.movieflix.data.repository.datasource.RemoteDataSource
import com.elnemr.movieflix.data.repository.datasource.RemoteDataSourceImpl
import com.elnemr.movieflix.domain.repository.IRepository
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