package com.alex.cooksample.data.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("name")
    var name: String?,

    @SerializedName("image_url")
    var photo: String?
)