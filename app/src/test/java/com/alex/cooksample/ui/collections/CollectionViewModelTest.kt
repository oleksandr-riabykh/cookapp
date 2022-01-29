package com.alex.cooksample.ui.collections

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alex.cooksample.data.repository.CollectionsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
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
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class CollectionViewModelTest {

    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private val collectionRepository: CollectionsRepository = mockk(relaxed = true)
    private lateinit var collectionViewModel: CollectionViewModel


    @Before
    fun setupTest() {
        Dispatchers.setMain(mainThreadSurrogate)
        collectionViewModel = CollectionViewModel(collectionRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `If view model getCollections calls repository's method`() {
        testDispatcher.runBlockingTest {
            //given
            coEvery { collectionRepository.getCollections() } returns mockk(relaxed = true)

            //when
            collectionViewModel.loadCollections()

            //then
            coVerify { collectionRepository.getCollections() }
        }
        testDispatcher.cleanupTestCoroutines()
    }

}