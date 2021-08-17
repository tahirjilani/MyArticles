package com.tahir.nyt.core.data.network

import com.google.gson.Gson
import com.tahir.nyt.core.data.ApiResponse
import retrofit2.Response
import javax.inject.Inject

class FakeErrorWebApi @Inject constructor(): WebApi {

    override suspend fun getPopularArticles(
        section: String,
        period: String
    ): Response<ApiResponse> {
        val body = Gson().fromJson(errorJson, ApiResponse::class.java)
        return Response.success(body)
    }

    private val errorJson = "{\n" +
            "   \"fault\":{\n" +
            "      \"faultstring\":\"Invalid ApiKey\",\n" +
            "      \"detail\":{\n" +
            "         \"errorcode\":\"oauth.v2.InvalidApiKey\"\n" +
            "      }\n" +
            "   }\n" +
            "}"
}