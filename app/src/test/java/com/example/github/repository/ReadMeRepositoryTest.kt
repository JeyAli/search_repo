package com.example.github.repository

import com.example.github.model.RepositoryModel
import com.example.github.model.RepositoryReadMeModel
import com.example.github.model.SearchResponseModel
import com.example.github.network.GitHubApiService
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito

@RunWith(JUnit4::class)
class ReadMeRepositoryTest {
    @Mock
    lateinit var mockGitHubApiService: GitHubApiService

    @Test
    fun readMeApi_success_test() = runBlocking {
        val repository = ReadMeRepository(mockGitHubApiService)

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
            .thenReturn(expectedModel)

        repository.fetchReadMe("login", "repo")

        val responseModel = repository.getReadMeModel().value!!

        assertEquals(expectedModel.encoding, responseModel.encoding)
        assertEquals(expectedModel.readMeURL, responseModel.readMeURL)
        assertEquals(expectedModel.size, responseModel.size)
        assertEquals(expectedModel.name, responseModel.name)
        assertEquals(expectedModel.url, responseModel.url)
    }

}