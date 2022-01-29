package com.alex.cooksample.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.alex.cooksample.Utils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class DataBaseTest {

    private lateinit var userDao: CollectionDao
    private lateinit var db: AppDatabase

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).setTransactionExecutor(testDispatcher.asExecutor())
            .setQueryExecutor(testDispatcher.asExecutor()).build()
        userDao = db.collectionDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.clearAllTables()
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun testIfDBSavesCollections() = runBlockingTest {
        //Given
        val testId = 5
        val collections = Utils.provideFakeCollection(testId)

        //When
        userDao.saveAll(collections)

        //Then
        val collection = userDao.getAll()
        assertThat(testId, equalTo(collection?.size))
    }
}