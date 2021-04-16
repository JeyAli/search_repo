package com.example.github.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.github.model.RepositoryModel
import com.example.github.repository.OrderByType
import com.example.github.repository.ReadMeRepository
import com.example.github.repository.SearchRepository
import com.example.github.repository.SortingType
import kotlinx.coroutines.launch

class RepositoriesViewModel(repository: SearchRepository, application: Application) : AndroidViewModel(application) {
    private val searchRepository = repository
    var repositories = searchRepository.getRepositories()

    fun search(name: String,
               sort: SortingType = SortingType.Stars,
               order: OrderByType = OrderByType.DESC) {
        viewModelScope.launch {
            searchRepository.searchRepo(name, sort, order)
        }
    }

    class RepositoriesViewModelFactory(val repository: SearchRepository, val application: Application)
        : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(
                SearchRepository::class.java,
                Application::class.java)
                .newInstance(repository, application)
        }
    }
}

