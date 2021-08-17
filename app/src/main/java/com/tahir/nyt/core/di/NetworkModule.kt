package com.tahir.nyt.core.di

import com.google.gson.Gson
import com.tahir.nyt.BuildConfig
import com.tahir.nyt.core.data.network.RequestInterceptor
import com.tahir.nyt.core.data.network.WebApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    /**
     * Provide Gson object to create GsonConverterFactory
     */
    @Provides
    fun providesGson(): Gson {
        return Gson()
    }


    /**
     * Provide Converter Factory to convert Json to our model objects
     */
    @Provides
    fun providesConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }


    @Provides
    fun providesHttpRequestInterceptor(): Interceptor {
        return RequestInterceptor()
    }


    /**
     * Provide OkHttpClient to initialize Retrofit instance
     */
    @Provides
    fun providesOkHttpClient(
        requestInterceptor: RequestInterceptor
    ): OkHttpClient {

        val builder = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)
            .readTimeout(50, TimeUnit.SECONDS)

        builder.addInterceptor(requestInterceptor)

        //this is just for debug purpose to see the logs.
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(logging)
        }
        return builder.build()
    }


    /**
     * Provide Retrofit object to create Webservice object which is used to call api endpoints
     */
    @Singleton
    @Provides
    fun providesRetrofit2(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    /**
     * Create an implementation of the NYT API endpoints. It will be used to call
     * apis
     */
    @Singleton
    @Provides
    fun providesWebservices(retrofit: Retrofit): WebApi {
        return retrofit.create(WebApi::class.java)
    }
}