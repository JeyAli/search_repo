package com.example.github.viewmodel

import android.app.Application
import com.example.github.model.RepositoryModel
import com.example.github.model.SearchResponseModel
import com.example.github.network.GitHubApiService
import com.example.github.repository.OrderByType
import com.example.github.repository.SearchRepository
import com.example.github.repository.SortingType
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito


@RunWith(JUnit4::class)
class RepositoriesViewModelTest {
    @Mock
    lateinit var mockApplication: Application

    @Mock
    lateinit var mockGitHubApiService: GitHubApiService

    @Test
    fun searchRepository_Success_Test()= runBlocking {
        val viewModel = RepositoriesViewModel(SearchRepository(mockGitHubApiService), mockApplication)

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
            anyString(),
            anyString(),
            anyString()))
            .thenReturn(expectedModel)

        viewModel.search("Demo", SortingType.Stars, OrderByType.DESC)

        val responseModel = viewModel.repositories.value!!

        assertEquals(responseModel[0].owner, expectedModel.repositories[0].owner)
        assertEquals(responseModel[0].fork, expectedModel.repositories[0].fork)
        assertEquals(responseModel[0].star, expectedModel.repositories[0].star)
        assertEquals(responseModel[0].issues, expectedModel.repositories[0].issues)
        assertEquals(responseModel[0].language, expectedModel.repositories[0].language)
        assertEquals(responseModel[0].name, expectedModel.repositories[0].name)
    }
}