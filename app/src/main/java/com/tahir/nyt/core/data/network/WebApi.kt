package com.tahir.nyt.core.data.network

import com.tahir.nyt.core.data.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WebApi {

    @GET("svc/mostpopular/v2/mostviewed/{section}/{period}.json")
    suspend fun getPopularArticles(
        @Path("section") section: String,
        @Path("period") period: String
    ): Response<ApiResponse>
}