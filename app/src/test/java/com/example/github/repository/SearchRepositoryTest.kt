package com.example.github.repository

import com.example.github.model.RepositoryModel
import com.example.github.model.RepositoryReadMeModel
import com.example.github.model.SearchResponseModel
import com.example.github.network.GitHubApiService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito

@RunWith(JUnit4::class)
class SearchRepositoryTest{
    @Mock
    lateinit var mockGitHubApiService: GitHubApiService

    @Test
    fun searchRepo_success_test() = runBlocking {
        val repository = SearchRepository(mockGitHubApiService)

        val expectedModel = SearchResponseModel( arrayListOf<RepositoryModel>((
                RepositoryModel(
                    fork = 10,
                    star = 12,
                    language = "Kotlin",
                    title = "Demo",
                    issues = 11,
                    name = "Jeyla",
                    owner = null
                )
                )))

        Mockito.`when`(mockGitHubApiService.getRepositories(
            ArgumentMatchers.anyString(),
            ArgumentMatchers.anyString(),
            ArgumentMatchers.anyString()
        ))
            .thenReturn(expectedModel)

        repository.searchRepo("demo", SortingType.Stars, OrderByType.DESC)

        val responseModel = repository.getRepositories().value!!

        assertEquals(responseModel[0].owner, expectedModel.repositories[0].owner)
        assertEquals(responseModel[0].fork, expectedModel.repositories[0].fork)
        assertEquals(responseModel[0].star, expectedModel.repositories[0].star)
        assertEquals(responseModel[0].issues, expectedModel.repositories[0].issues)
        assertEquals(responseModel[0].language, expectedModel.repositories[0].language)
        assertEquals(responseModel[0].name, expectedModel.repositories[0].name)
    }

}