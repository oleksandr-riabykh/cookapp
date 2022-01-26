package com.alex.cooksample.data.models

import com.google.gson.annotations.SerializedName

class CookCollection(
    @SerializedName("id")
    var id: Int?,

    @SerializedName("title")
    var title: String?,

    @SerializedName("description")
    var description: String?,

    @SerializedName("recipe_count")
    var recipeCount: Int?,

    @SerializedName("preview_image_urls")
    var preview: ArrayList<String>?
)