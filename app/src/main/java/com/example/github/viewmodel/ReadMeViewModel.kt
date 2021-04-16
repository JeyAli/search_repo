package com.example.github.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.github.repository.OrderByType
import com.example.github.repository.ReadMeRepository
import com.example.github.repository.SearchRepository
import com.example.github.repository.SortingType
import kotlinx.coroutines.launch

class ReadMeViewModel (repository: ReadMeRepository, application: Application) : AndroidViewModel(application) {
    private val readMeRepository = repository
    var readMeModel = readMeRepository.getReadMeModel()

    fun fetchReadMe(login:String, name:String) {
        viewModelScope.launch {
            readMeRepository.fetchReadMe(login, name)
        }
    }

    class ReadMeViewModelFactory(val repository: ReadMeRepository, val application: Application)
        : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(ReadMeRepository::class.java,
                                             Application::class.java)
                .newInstance(repository, application)
        }
    }
}