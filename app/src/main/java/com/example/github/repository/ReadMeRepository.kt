package com.example.github.repository

import androidx.lifecycle.MutableLiveData
import com.example.github.Constants
import com.example.github.model.RepositoryModel
import com.example.github.model.RepositoryReadMeModel
import com.example.github.network.GitHubApiService
import com.example.github.network.Network

class ReadMeRepository {
    private val gitHubApiService: GitHubApiService = Network.gitHubService
    private var readMeModel: MutableLiveData<RepositoryReadMeModel> =
        MutableLiveData<RepositoryReadMeModel>()


    fun getReadMeModel(): MutableLiveData<RepositoryReadMeModel> {
        return readMeModel
    }

    suspend fun fetchReadMe(login: String, name: String) {
        val m = gitHubApiService.getReadMe(login, name)
        readMeModel.postValue(m)
    }
}