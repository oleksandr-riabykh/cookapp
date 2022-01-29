package com.alex.cooksample.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CollectionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(checkEntity: List<CollectionEntity>)

    @Query("SELECT * FROM check_entity ORDER BY id")
    fun getAll(): List<CollectionEntity>?
}