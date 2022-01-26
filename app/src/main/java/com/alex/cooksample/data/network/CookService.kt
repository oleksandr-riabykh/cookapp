package com.alex.cooksample.data.network

import com.alex.cooksample.data.models.CookCollection
import com.alex.cooksample.data.models.Recipe
import retrofit2.http.GET
import retrofit2.http.Path

interface CookService {
    @GET(ENDPOINT_COLLECTIONS)
    suspend fun getCollection(): List<CookCollection>

    @GET(ENDPOINT_COLLECTION_RECIPES)
    suspend fun getRecipesByCollectionId(@Path("id") id: Int): List<Recipe>

    @GET(ENDPOINT_COLLECTION_BY_ID)
    suspend fun getCollectionById(@Path("id") id: Int): CookCollection

    @GET(ENDPOINT_RECIPES)
    suspend fun getRecipes(): List<Recipe>

    @GET(ENDPOINT_RECIPE_BY_ID)
    suspend fun getRecipeById(@Path("id") id: Int): Recipe
}