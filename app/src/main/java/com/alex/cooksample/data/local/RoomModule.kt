package com.alex.cooksample.data.local

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

private const val DB_NAME = "cook.db"

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    fun provideRoom(
        @ApplicationContext applicationContext: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, DB_NAME
        ).build()
    }

    @Provides
    fun provideUserDao(
        appDatabase: AppDatabase
    ): CollectionDao {
        return appDatabase.collectionDao()
    }
}