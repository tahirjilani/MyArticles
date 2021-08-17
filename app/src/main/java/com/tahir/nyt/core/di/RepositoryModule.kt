package com.tahir.nyt.core.di

import com.tahir.nyt.core.data.ArticleRepositoryImpl
import com.tahir.nyt.core.data.network.WebApi
import com.tahir.nyt.core.domain.ArticleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    fun provideArticleRepository(webApi: WebApi): ArticleRepository {
        return ArticleRepositoryImpl(webApi)
    }
}