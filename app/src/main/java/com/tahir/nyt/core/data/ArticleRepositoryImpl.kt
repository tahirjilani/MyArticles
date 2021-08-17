package com.tahir.nyt.core.data

import com.tahir.nyt.core.data.network.WebApi
import com.tahir.nyt.core.domain.Article
import com.tahir.nyt.core.domain.ArticleRepository
import com.tahir.nyt.core.domain.Resource
import java.lang.Exception
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(private val webApi: WebApi) : ArticleRepository() {

    override suspend fun getArticles(section: String, period: String): Resource<List<Article>> {
        return try {
            val apiResponse = webApi.getPopularArticles(section, period)
            if (apiResponse.isSuccessful) {

                val body = apiResponse.body()
                when {
                    body?.fault != null -> {
                        Resource.Error(Exception(body.fault.faultString))
                    }
                    body?.status == "OK" -> {
                        Resource.Success(body.results)
                    }
                    else -> {
                        Resource.Error(Exception("Something went wrong"))
                    }
                }
            } else {
                Resource.Error(Exception(apiResponse.message()))
            }

        } catch (ex: Exception) {
            Resource.Error(ex)
        }
    }


}