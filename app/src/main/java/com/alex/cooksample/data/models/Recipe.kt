package com.alex.cooksample.data.models

import com.google.gson.annotations.SerializedName

data class Recipe(
    @SerializedName("id")
    var id: Int?,

    @SerializedName("title")
    var title: String?,

    @SerializedName("story")
    var story: String?,

    @SerializedName("image_url")
    var image_url: String?,

    @SerializedName("published_at")
    var publishedAt: String?,

    @SerializedName("ingredients")
    var ingredients: List<String>?,

    @SerializedName("user")
    var user: User?,

    @SerializedName("steps")
    var steps: List<Step>?,
)