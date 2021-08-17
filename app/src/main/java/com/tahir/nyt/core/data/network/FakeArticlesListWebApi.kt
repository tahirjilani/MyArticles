package com.tahir.nyt.core.data.network

import com.google.gson.Gson
import com.tahir.nyt.core.data.ApiResponse
import retrofit2.Response
import javax.inject.Inject

class FakeArticlesListWebApi @Inject constructor(): WebApi {

    override suspend fun getPopularArticles(
        section: String,
        period: String
    ): Response<ApiResponse> {
        val body = Gson().fromJson(fakeRecordsJson, ApiResponse::class.java)
        return Response.success(body)
    }

    private val fakeRecordsJson = "{\n" +
            "\t\"status\": \"OK\",\n" +
            "\t\"copyright\": \"Copyright (c) 2021 The New York Times Company.  All Rights Reserved.\",\n" +
            "\t\"num_results\": 20,\n" +
            "\t\"results\": [{\n" +
            "\t\t\t\"uri\": \"nyt://article/78d8f3ad-b7e9-5464-b218-93220e85ef3e\",\n" +
            "\t\t\t\"url\": \"https://www.nytimes.com/2021/08/12/health/metabolism-weight-aging.html\",\n" +
            "\t\t\t\"id\": 100000007918021,\n" +
            "\t\t\t\"asset_id\": 100000007918021,\n" +
            "\t\t\t\"source\": \"New York Times\",\n" +
            "\t\t\t\"published_date\": \"2021-08-12\",\n" +
            "\t\t\t\"updated\": \"2021-08-16 22:19:55\",\n" +
            "\t\t\t\"section\": \"Health\",\n" +
            "\t\t\t\"subsection\": \"\",\n" +
            "\t\t\t\"nytdsection\": \"health\",\n" +
            "\t\t\t\"adx_keywords\": \"\",\n" +
            "\t\t\t\"column\": null,\n" +
            "\t\t\t\"byline\": \"By Gina Kolata\",\n" +
            "\t\t\t\"type\": \"Article\",\n" +
            "\t\t\t\"title\": \"What We Think We Know About Metabolism May Be Wrong\",\n" +
            "\t\t\t\"abstract\": \"\",\n" +
            "\t\t\t\"des_facet\": [\n" +
            "\t\t\t\t\"Weight\"\n" +
            "\t\t\t],\n" +
            "\t\t\t\"org_facet\": [\n" +
            "\t\t\t\t\"Science (Journal)\"\n" +
            "\t\t\t],\n" +
            "\t\t\t\"per_facet\": [],\n" +
            "\t\t\t\"geo_facet\": [],\n" +
            "\t\t\t\"media\": [{\n" +
            "\t\t\t\t\"type\": \"image\",\n" +
            "\t\t\t\t\"subtype\": \"photo\",\n" +
            "\t\t\t\t\"caption\": \"\",\n" +
            "\t\t\t\t\"copyright\": \"\",\n" +
            "\t\t\t\t\"approved_for_syndication\": 1,\n" +
            "\t\t\t\t\"media-metadata\": [{\n" +
            "\t\t\t\t\t\"url\": \"https://static01.nyt.com/images/2021/08/17/science/12sci-metabolism/12sci-metabolism-thumbStandard.jpg\",\n" +
            "\t\t\t\t\t\"format\": \"Standard Thumbnail\",\n" +
            "\t\t\t\t\t\"height\": 75,\n" +
            "\t\t\t\t\t\"width\": 75\n" +
            "\t\t\t\t}]\n" +
            "\t\t\t}],\n" +
            "\t\t\t\"eta_id\": 0\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"uri\": \"nyt://article/ac4c37c2-a7f8-5a9a-ade2-ebdb4c5115b3\",\n" +
            "\t\t\t\"url\": \"https://www.nytimes.com/article/covid-breakthrough-delta-variant.html\",\n" +
            "\t\t\t\"id\": 100000007912869,\n" +
            "\t\t\t\"asset_id\": 100000007912869,\n" +
            "\t\t\t\"source\": \"New York Times\",\n" +
            "\t\t\t\"published_date\": \"2021-08-10\",\n" +
            "\t\t\t\"updated\": \"2021-08-14 09:27:40\",\n" +
            "\t\t\t\"section\": \"Health\",\n" +
            "\t\t\t\"subsection\": \"\",\n" +
            "\t\t\t\"nytdsection\": \"health\",\n" +
            "\t\t\t\"adx_keywords\": \"\",\n" +
            "\t\t\t\"column\": null,\n" +
            "\t\t\t\"byline\": \"By Apoorva Mandavilli\",\n" +
            "\t\t\t\"type\": \"Article\",\n" +
            "\t\t\t\"title\": \"What to Know About Breakthrough Infections and the Delta Variant\",\n" +
            "\t\t\t\"abstract\": \".\",\n" +
            "\t\t\t\"des_facet\": [\n" +
            "\t\t\t\t\"your-feed-science\"\n" +
            "\t\t\t],\n" +
            "\t\t\t\"org_facet\": [\n" +
            "\t\t\t\t\"Centers for Disease Control and Prevention\"\n" +
            "\t\t\t],\n" +
            "\t\t\t\"per_facet\": [],\n" +
            "\t\t\t\"geo_facet\": [\n" +
            "\t\t\t\t\"United States\"\n" +
            "\t\t\t],\n" +
            "\t\t\t\"media\": [{\n" +
            "\t\t\t\t\"type\": \"image\",\n" +
            "\t\t\t\t\"subtype\": \"photo\",\n" +
            "\t\t\t\t\"caption\": \".\",\n" +
            "\t\t\t\t\"copyright\": \"Matthew Odom for The New York Times\",\n" +
            "\t\t\t\t\"approved_for_syndication\": 1,\n" +
            "\t\t\t\t\"media-metadata\": [{\n" +
            "\t\t\t\t\t\"url\": \"https://static01.nyt.com/images/2021/08/10/science/00virus-breakthrough2/00virus-breakthrough2-thumbStandard.jpg\",\n" +
            "\t\t\t\t\t\"format\": \"Standard Thumbnail\",\n" +
            "\t\t\t\t\t\"height\": 75,\n" +
            "\t\t\t\t\t\"width\": 75\n" +
            "\t\t\t\t}]\n" +
            "\t\t\t}],\n" +
            "\t\t\t\"eta_id\": 0\n" +
            "\t\t}\n" +
            "\t]\n" +
            "}"
}