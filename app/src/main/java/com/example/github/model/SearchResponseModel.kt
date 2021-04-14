package com.example.github.model

import com.google.gson.annotations.SerializedName

data class SearchResponseModel(
    @SerializedName("items")
    var repositories: ArrayList<RepositoryModel>
)
