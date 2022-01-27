package com.alex.cooksample.data.repository

import com.alex.cooksample.data.local.CollectionDao
import com.alex.cooksample.data.models.CookCollection
import com.alex.cooksample.data.network.CookService
import com.alex.cooksample.extensions.toCollectionEntity
import com.alex.cooksample.extensions.toCollectionModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class CollectionsRepository @Inject constructor(
    private val collectionDao: CollectionDao,
    private val cookService: CookService
) {
    //sync made just in simple way. The logic might be improved
    suspend fun getCollections(): List<CookCollection> = withContext(Dispatchers.IO) {
        val localData = getLocalCollections()
        return@withContext if (localData?.isNullOrEmpty() == true) {
            val collection = cookService.getCollections()
            collectionDao.saveAll(collection.map { it.toCollectionEntity() })
            collection
        } else {
            localData
        }
    }

    suspend fun getCollectionById(id: Int): CookCollection = cookService.getCollectionById(id)

    private fun getLocalCollections() =
        collectionDao.getAll()?.map { it.toCollectionModel() }
}