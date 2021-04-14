package com.example.github.model

import com.google.gson.annotations.SerializedName

data class RepositoryReadMeModel (
        @SerializedName("content")
        var content: String?,

        @SerializedName("encoding")
        var encoding: String?,

        @SerializedName("size")
        var size: Int?,

        @SerializedName("name")
        var name: String?,

        @SerializedName("url")
        var url: String?
)