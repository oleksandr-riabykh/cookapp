package com.alex.cooksample.data.models

import com.google.gson.annotations.SerializedName

data class Step(
    @SerializedName("description")
    var description: String?,

    @SerializedName("image_urls")
    var images: List<String>?
)