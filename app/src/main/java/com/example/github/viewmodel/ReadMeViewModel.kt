package com.example.github.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.github.repository.OrderByType
import com.example.github.repository.ReadMeRepository
import com.example.github.repository.SearchRepository
import com.example.github.repository.SortingType
import kotlinx.coroutines.launch

class ReadMeViewModel (application: Application) : AndroidViewModel(application) {
    private val readMeRepository = ReadMeRepository()
    var readMeModel = readMeRepository.getReadMeModel()


    fun fetchReadMe(login:String, name:String) {
        viewModelScope.launch {
            readMeRepository.fetchReadMe(login, name)
        }
    }
}