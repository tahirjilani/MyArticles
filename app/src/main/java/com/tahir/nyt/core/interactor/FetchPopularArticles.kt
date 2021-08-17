package com.tahir.nyt.core.interactor

import com.tahir.nyt.core.domain.ArticleRepository
import javax.inject.Inject

/**
 * Interactor class to FetchPopularArticles. This is used by the app to fetch records from
 * the WebApi for production code or fake mocked api's for testing purpose
 */
class FetchPopularArticles
        @Inject constructor(private val repository: ArticleRepository) {
    suspend operator fun invoke(section: String, period: String) = repository.getArticles(section, period)
}