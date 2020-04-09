package uk.co.diegobarle.core.network.servermodel

import com.google.gson.annotations.SerializedName

data class SResponse<T>(
    @SerializedName("page") val page: String?,

    @SerializedName("total_results") val totalResults: String?,

    @SerializedName("total_pages") val totalPages: String?,

    @SerializedName("results") val results: T
)