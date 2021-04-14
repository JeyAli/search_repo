package com.example.github.model

import com.google.gson.annotations.SerializedName

data class RepositoryModel (
    @SerializedName("forks")
    var fork: Int?,

    @SerializedName("stargazers_count")
    var star: Int?,

    @SerializedName("full_name")
    var title: String?,

    @SerializedName("open_issues")
    var issues: Int?,

    @SerializedName("owner")
    var owner: OwnerModel?
)