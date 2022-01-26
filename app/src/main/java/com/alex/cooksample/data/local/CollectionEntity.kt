package com.alex.cooksample.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "check_entity")
class CollectionEntity {
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int? = 0

    @ColumnInfo(name = "title")
    var title: String? = null

    @ColumnInfo(name = "description")
    var description: String? = null

    @ColumnInfo(name = "recipe_count")
    var recipeCount: Int? = 0

    @ColumnInfo(name = "preview_image_urls")
    @TypeConverters(ListConverter::class)
    var preview: ArrayList<String>? = null

    override fun toString(): String {
        return "CollectionEntity(id='$id', title=$title, description=$description, recipeCount=$recipeCount, preview=$preview"
    }
}