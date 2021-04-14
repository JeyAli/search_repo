package com.example.github.model

import com.google.gson.annotations.SerializedName

data class OwnerModel(
    @SerializedName("node_id")
    val nodeId: String?,

    @SerializedName("avatar_url")
    val imageUrl: String?
)
