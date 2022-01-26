package com.alex.cooksample.data.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val SERVICE_HOST = "cookpad.github.io/global-mobile-hiring"
private const val SERVICE_PROTOCOL = "https"
private const val API_VERSION = "api"


const val ENDPOINT_COLLECTIONS = "collections/"
const val ENDPOINT_COLLECTION_RECIPES = "collections/{id}/recipes/"
const val ENDPOINT_RECIPES = "recipes/"
const val ENDPOINT_RECIPE_BY_ID = "recipes/{id}/"
const val ENDPOINT_COLLECTION_BY_ID = "collections/{id}/"

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    fun providesCookApi(): CookService {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("$SERVICE_PROTOCOL://$SERVICE_HOST/$API_VERSION/")
            .build()
        return retrofit.create(CookService::class.java)
    }
}