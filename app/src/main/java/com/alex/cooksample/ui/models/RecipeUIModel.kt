package com.alex.cooksample.ui.models

import com.alex.cooksample.data.models.Step
import com.alex.cooksample.data.models.User

class RecipeUIModel(
    var id: Int?,
    var title: String?,
    var story: String?,
    var imageUrl: String = "",
    var publishedAt: String?,
    var ingredients: List<String>?,
    var user: User?,
    var steps: List<StepUIModel>?
)