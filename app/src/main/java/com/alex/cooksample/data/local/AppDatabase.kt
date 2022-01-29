package com.alex.cooksample.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [CollectionEntity::class], version = 1)
@TypeConverters(ListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun collectionDao(): CollectionDao
}