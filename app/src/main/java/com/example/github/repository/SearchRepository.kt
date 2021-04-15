package com.example.github.repository

import androidx.lifecycle.MutableLiveData
import com.example.github.Constants.SEARCH_SIZE
import com.example.github.model.RepositoryModel
import com.example.github.network.GitHubApiService
import com.example.github.network.Network


public class SearchRepository {
    private val gitHubApiService: GitHubApiService = Network.gitHubService
    private var repositories: MutableLiveData<ArrayList<RepositoryModel>> =
        MutableLiveData<ArrayList<RepositoryModel>>()


    fun getRepositories(): MutableLiveData<ArrayList<RepositoryModel>> {
        return repositories
    }

    suspend fun searchRepo(name: String, sort: SortingType, order: OrderByType) {
        var sortParam = "stars"
        var orderParam = "desc"

        if(sort == SortingType.Forks) {
            sortParam = "forks"
        }

        if(order == OrderByType.ASC) {
            orderParam = "asc"
        }

        repositories.postValue(ArrayList(
            gitHubApiService.getRepositories(
                name, sortParam, orderParam).repositories.take(SEARCH_SIZE)))
    }
}