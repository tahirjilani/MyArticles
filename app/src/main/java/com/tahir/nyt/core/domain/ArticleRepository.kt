package com.tahir.nyt.core.domain

/**
 * Abstract class to fetch articles against provided parameters and credentials
 */
abstract class ArticleRepository {
    abstract suspend fun getArticles(section: String, period: String): Resource<List<Article>>
}