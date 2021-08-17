package com.tahir.nyt.core.data


import com.google.gson.annotations.SerializedName
import com.tahir.nyt.core.domain.Article

data class ApiResponse(
    @SerializedName("status")
    val status: String,

    @SerializedName("copyright")
    val copyright: String,

    @SerializedName("num_results")
    val numResults: Long,

    @SerializedName("results")
    val results: List<Article>,

    @SerializedName("fault")
    val fault: Fault?,

) {
    data class Fault(
        @SerializedName("detail")
        val detail: Detail,

        @SerializedName("faultstring")
        val faultString: String
    ) {
        data class Detail(
            @SerializedName("errorcode")
            val errorCode: String
        )
    }
}