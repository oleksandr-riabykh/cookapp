package com.alex.cooksample.data.repository

import com.alex.cooksample.data.local.CollectionDao
import com.alex.cooksample.data.network.CookService
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class CollectionRepositoryTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private val testDispatcher = TestCoroutineDispatcher()

    private val collectionDao: CollectionDao = mockk(relaxed = true)
    private val networkService: CookService = mockk(relaxed = true)
    private lateinit var collectionRepo: CollectionsRepository


    @Before
    fun setupTest() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        Dispatchers.setMain(mainThreadSurrogate)
        collectionRepo = CollectionsRepository(collectionDao, networkService, testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
        clearAllMocks()
    }

    @Test
    fun `If get collections calls get all in the CollectionDao`() {
        testDispatcher.runBlockingTest {
            //given
            coEvery { collectionDao.getAll() } returns listOf()
            coEvery { networkService.getCollections() } returns mockk(relaxed = true)

            //when
            collectionRepo.getCollections()

            //then
            coVerify { collectionDao.getAll() }
        }
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `If getCollectionById calls method CookService getCollectionById`() {
        testDispatcher.runBlockingTest {
            val collectionId = 2

            //given
            coEvery { networkService.getCollectionById(collectionId) } returns mockk(relaxed = true)

            //when
            collectionRepo.getCollectionById(collectionId)

            //then
            coVerify { networkService.getCollectionById(collectionId) }
        }
        testDispatcher.cleanupTestCoroutines()
    }
}