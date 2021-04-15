package com.example.github.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.github.R
import com.example.github.adapter.RepositoryRecyclerAdapter
import com.example.github.viewmodel.RepositoriesViewModel

class RepositorySearchFragment: Fragment() {

    private val viewModel: RepositoriesViewModel by activityViewModels()
    private val adapter = RepositoryRecyclerAdapter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.repositories.observe(viewLifecycleOwner, Observer {
                updatedRepoList -> adapter.setRepositories(updatedRepoList)
        })
    }
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.fragment_search_repos)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        val keyword:EditText = view.findViewById(R.id.txtSearch)

        val searchBtn: ImageButton = view.findViewById(R.id.btnSearch)
        searchBtn.setOnClickListener { performSearch(keyword.text.toString()) }

        return view
    }

    private fun performSearch(keyword: String) {
        viewModel.search(keyword)
    }
}
