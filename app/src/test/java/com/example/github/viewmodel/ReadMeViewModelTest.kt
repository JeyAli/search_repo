package com.example.github.viewmodel

import android.app.Application
import com.example.github.model.RepositoryModel
import com.example.github.model.RepositoryReadMeModel
import com.example.github.model.SearchResponseModel
import com.example.github.network.GitHubApiService
import com.example.github.repository.OrderByType
import com.example.github.repository.ReadMeRepository
import com.example.github.repository.SearchRepository
import com.example.github.repository.SortingType
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito

@RunWith(JUnit4::class)
class ReadMeViewModelTest {
    @Mock
    lateinit var mockApplication: Application

    @Mock
    lateinit var mockGitHubApiService: GitHubApiService

    @Test
    fun readMeRepository_Success_Test()= runBlocking {
        val viewModel = ReadMeViewModel(ReadMeRepository(mockGitHubApiService), mockApplication)

        val expectedModel = RepositoryReadMeModel(
            readMeURL = "http://test.com",
            encoding = "base64",
            size=10,
            name="Jeyla",
            url = null
        )

        Mockito.`when`(mockGitHubApiService.getReadMe(
            ArgumentMatchers.anyString(),
            ArgumentMatchers.anyString(),
        ))

        viewModel.fetchReadMe(anyString(), anyString())

        val responseModel = viewModel.readMeModel.value!!

        assertEquals(expectedModel.encoding, responseModel.encoding)
        assertEquals(expectedModel.readMeURL, responseModel.readMeURL)
        assertEquals(expectedModel.size, responseModel.size)
        assertEquals(expectedModel.name, responseModel.name)
        assertEquals(expectedModel.url, responseModel.url)
    }
}