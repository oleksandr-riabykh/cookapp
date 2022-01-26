package com.alex.cooksample.ui.models


class CookCollectionUIModel(
    var id: Int?,
    var title: String?,
    var description: String?,
    var recipeCount: Int?,
    var previews: List<String> = listOf()
) {
    override fun toString(): String {
        return "CookCollectionUIModel(title=$title, description=$description, recipeCount=$recipeCount)"
    }
}