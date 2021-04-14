package com.example.github.network

import com.example.github.Constants
import com.example.github.model.RepositoryReadMeModel
import com.example.github.model.RepositoryModel
import com.example.github.model.SearchResponseModel
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface GitHubApiService {
    @Headers("Accept: application/vnd.github.v3+json")
    @GET("search/repositories")
    suspend fun getRepositories(
        @Query("q") query: String,
        @Query("sort") sort: String,
        @Query("order") order: String,
    ): SearchResponseModel

    @Headers("Accept: application/vnd.github.v3+json")
    @GET("repos/{full_name}/readme")
    suspend fun getReadMe(
        @Path("full_name") repo: String
    ): RepositoryReadMeModel
}

object Network {
    val gitHubService: GitHubApiService by lazy {
        var logging = HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        var httpClient = OkHttpClient.Builder();
        httpClient.addInterceptor(logging)
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .client(httpClient.build())
            .build()

        retrofit.create(GitHubApiService::class.java)
    }
}