package com.alex.cooksample

import com.alex.cooksample.data.local.CollectionEntity

class Utils {
    companion object {
        fun provideFakeCollection(recordsNumber: Int): List<CollectionEntity> =
            MutableList(recordsNumber) { index ->
                CollectionEntity(
                    id = index,
                    title = "title $index",
                    description = "description $index",
                    recipeCount = index,
                    preview = arrayListOf("testpreview #$index"),
                )
            }

    }
}