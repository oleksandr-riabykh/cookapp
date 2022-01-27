package com.alex.cooksample.data.repository

import com.alex.cooksample.BaseTest
import com.alex.cooksample.data.local.CollectionDao
import com.alex.cooksample.data.network.CookService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CollectionRepositoryTest : BaseTest() {

    private val collectionDao: CollectionDao = mockk(relaxed = true)
    private val networkService: CookService = mockk(relaxed = true)

    private lateinit var collectionRepo: CollectionsRepository

    @Before
    fun setupTest() {
        collectionRepo = CollectionsRepository(collectionDao, networkService)
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