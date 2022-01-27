package com.alex.cooksample.data.repository

import com.alex.cooksample.data.local.CollectionDao
import com.alex.cooksample.data.network.CookService
import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class CollectionRepositoryTest {
    @ObsoleteCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private val collectionDao: CollectionDao = mockk(relaxed = true)
    private val networkService: CookService = mockk(relaxed = true)
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO

    private lateinit var collectionRepo: CollectionsRepository

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @Before
    fun setupTest() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        Dispatchers.setMain(mainThreadSurrogate)
        collectionRepo = CollectionsRepository(collectionDao, networkService, coroutineDispatcher)
    }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
        clearAllMocks()
    }

    @Test
    fun `If no cached collections then get response from server`() = runBlocking {
        //given
        coEvery { collectionDao.getAll() } returns listOf()
        coEvery { networkService.getCollections() } returns mockk(relaxed = true)

        //when
        collectionRepo.getCollections()

        //then
        coVerify { networkService.getCollections() }
    }

    @Test
    fun `If collections then get collection by id from server is not empty`() = runBlocking {

        val collectionId = 2

        //given
        coEvery { networkService.getCollectionById(collectionId) } returns mockk(relaxed = true)

        //when
        collectionRepo.getCollectionById(collectionId)

        //then
        coVerify { networkService.getCollectionById(collectionId) }
    }
}