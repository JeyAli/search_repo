package com.example.github.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.github.model.RepositoryModel
import com.example.github.repository.OrderByType
import com.example.github.repository.SearchRepository
import com.example.github.repository.SortingType
import kotlinx.coroutines.launch

class RepositoriesViewModel(application: Application) : AndroidViewModel(application) {
    private val searchRepository = SearchRepository()
    var repositories = searchRepository.getRepositories()

    fun search(name: String,
               sort: SortingType = SortingType.Stars,
               order: OrderByType = OrderByType.DESC) {
        viewModelScope.launch {
            searchRepository.searchRepo(name, sort, order)
        }
    }
}

