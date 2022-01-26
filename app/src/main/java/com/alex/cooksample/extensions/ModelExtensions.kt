package com.alex.cooksample.extensions

import com.alex.cooksample.data.local.CollectionEntity
import com.alex.cooksample.data.models.CookCollection
import com.alex.cooksample.data.models.Recipe
import com.alex.cooksample.data.models.Step
import com.alex.cooksample.ui.models.CookCollectionUIModel
import com.alex.cooksample.ui.models.RecipeUIModel
import com.alex.cooksample.ui.models.StepUIModel

fun CollectionEntity.toCollectionModel(): CookCollection {
    return CookCollection(
        id = this.id,
        description = this.description,
        title = this.title,
        recipeCount = this.recipeCount,
        preview = this.preview
    )
}

fun CookCollection.toCollectionEntity(): CollectionEntity {

    val entity = CollectionEntity()
    entity.id = this.id
    entity.description = this.description
    entity.title = this.title
    entity.recipeCount = this.recipeCount
    entity.preview = this.preview
    return entity
}

fun CookCollection.toCollectionUIModel(): CookCollectionUIModel {
    return CookCollectionUIModel(
        id = this.id,
        description = this.description,
        title = this.title,
        recipeCount = this.recipeCount,
        previews = this.preview ?: listOf()
    )
}

fun Recipe.toRecipeUIModel(): RecipeUIModel {
    return RecipeUIModel(
        id = this.id,
        story = this.story,
        title = this.title,
        imageUrl = this.image_url ?: "",
        user = this.user,
        publishedAt = this.publishedAt,
        ingredients = this.ingredients,
        steps = this.steps?.map { it.toStepUIModel() }
    )
}

fun Step.toStepUIModel(): StepUIModel {
    val notEmpty = this.images?.isNotEmpty() ?: false
    return StepUIModel(
        description = this.description,
        imageUrl = if (notEmpty) this.images?.first() ?: "" else "",
    )
}