package com.example.github.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.github.R
import com.example.github.model.RepositoryModel
import com.example.github.viewmodel.RepositoriesViewModel

class RepositoryRecyclerAdapter: RecyclerView.Adapter<RepositoryRecyclerAdapter.RepositoriesViewHolder>() {
    private var repositories = ArrayList<RepositoryModel>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RepositoryRecyclerAdapter.RepositoriesViewHolder {
        var itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.repository_item,parent,false)
        return RepositoriesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RepositoryRecyclerAdapter.RepositoriesViewHolder, position: Int) {
        holder.repoName.text = repositories[position].title

        if (repositories[position].star != null) {
            var startCount = repositories[position].star!!.toDouble()

            if (startCount > 1000) {
                holder.stars.text = String.format("%.1f", startCount/1000) + "k"
            } else {
                holder.stars.text = startCount.toInt().toString()
            }
        }

        if (repositories[position].fork != null) {
            var forkCount = repositories[position].fork!!.toDouble()

            if (forkCount > 1000) {
                holder.forks.text = String.format("%.1f", forkCount/1000) + "k"
            } else {
                holder.forks.text = forkCount.toInt().toString()
            }
        }

        Glide.with(holder.itemView)
            .load(repositories[position].owner?.imageUrl)
            .into(holder.thumbnailAvatar)
    }

    override fun getItemCount(): Int {
        return repositories.size
    }

    public fun setRepositories(repos: ArrayList<RepositoryModel>) {
        this.repositories = repos
        notifyDataSetChanged()
    }

    inner class RepositoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val repoName: TextView = view.findViewById(R.id.repo_title)
        val stars: TextView = view.findViewById(R.id.repo_stars)
        val forks: TextView = view.findViewById(R.id.repo_forks)
        val thumbnailAvatar: ImageView = view.findViewById(R.id.repo_avatar)
    }

}