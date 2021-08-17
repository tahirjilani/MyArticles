package com.tahir.nyt.core.interactor

import com.tahir.nyt.core.data.ArticleRepositoryImpl
import com.tahir.nyt.core.data.network.FakeArticlesListWebApi
import com.tahir.nyt.core.data.network.FakeErrorWebApi
import com.tahir.nyt.core.domain.Resource
import junit.framework.TestCase
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class FetchPopularArticlesUnitTest {

    private lateinit var fakeFetchPopularArticles: FetchPopularArticles
    private lateinit var fakeErrorFetchPopularArticles: FetchPopularArticles

    @Before
    fun init() {
        fakeFetchPopularArticles = FetchPopularArticles(ArticleRepositoryImpl(FakeArticlesListWebApi()))
        fakeErrorFetchPopularArticles = FetchPopularArticles(ArticleRepositoryImpl(FakeErrorWebApi()))
    }

    @Test
    fun givenFakeApiResponse_load_loadsArticles() = runBlockingTest {
        //doesn't matter if we pass these params empty here
        val resource = fakeFetchPopularArticles.invoke("", "")
        when(resource){
            is Resource.Success -> {
                assert(resource.data.isNotEmpty())
            }
            else -> {
            assert(false)
        }
        }

    }

    @Test
    fun givenFakeErrorApiResponse_load_returnsError() = runBlockingTest {
        //doesn't matter if we pass these params empty here
        val resource = fakeErrorFetchPopularArticles.invoke("", "")
        when(resource){
            is Resource.Error -> {
                assertEquals("Invalid ApiKey", resource.exception.message)
            }
            else -> {
                assert(false)
            }
        }

    }

}