package com.elnemr.core.data.di

import com.elnemr.core.data.apiservice.ApiInterface
import com.elnemr.core.data.util.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder()

    @Singleton
    @Provides
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder = OkHttpClient.Builder()

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()

    @Singleton
    @Provides
    fun provideApiKeyInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            val url = request.url
                .newBuilder()
                .addQueryParameter("api_key", Constants.API_KEY)
                .build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().add(ApplicationJsonAdapterFactory.INSTANCE).build()

    @Singleton
    @Provides
    fun provideConverterFactory(moshi: Moshi): Converter.Factory =
        MoshiConverterFactory.create(moshi)

    @Singleton
    @Provides
    fun provideApiInterface(
        builder: Retrofit.Builder,
        okHttpClientBuilder: OkHttpClient.Builder,
        apiKeyInterceptor: Interceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        converterFactory: Converter.Factory
    ): ApiInterface {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClientBuilder.addNetworkInterceptor(httpLoggingInterceptor)
        okHttpClientBuilder.interceptors().add(apiKeyInterceptor)

        okHttpClientBuilder.connectTimeout(1, TimeUnit.MINUTES)
        okHttpClientBuilder.readTimeout(1, TimeUnit.MINUTES)
        okHttpClientBuilder.retryOnConnectionFailure(false)
        val client = okHttpClientBuilder.build()
        return builder.client(client)
            .baseUrl(Constants.BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(converterFactory)
            .build()
            .create(ApiInterface::class.java)
    }

}