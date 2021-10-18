package com.example.githubapp.ui.main

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubapp.R
import com.example.githubapp.models.Repo
/**
 * Created by Asmaa Hassan
 */
class StarredReposAdapter(private val context: Context): PagingDataAdapter<Repo, StarredReposAdapter.ViewHolder>(DataDifferntiator) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        var tvRepoName: TextView? = null
        var tvRepoDescription: TextView? = null
        var tvUsername: TextView? = null
        var avatar: ImageView
        var ratingBar: RatingBar? = null
        init {
            tvRepoName = view.findViewById(R.id.tvRepoName)
            tvRepoDescription =view.findViewById(R.id.tvRepoDescription)
            tvUsername = view.findViewById(R.id.tvUsername)
            avatar = view.findViewById(R.id.imAvatar)
            ratingBar = view.findViewById(R.id.ratingBar)
        }
    }

    override fun onBindViewHolder(holder: StarredReposAdapter.ViewHolder, position: Int) {
        holder.tvRepoName?.text =
            "${getItem(position)?.fullName}"
        holder.tvRepoDescription?.text =
            "${getItem(position)?.description}"
        holder.tvUsername?.text =
            "${getItem(position)?.owner?.login}"
        holder.ratingBar?.rating = getItem(position)?.stargazersCount?.toFloat()!!

        Glide.with(context).load("${getItem(position)?.owner?.avatarUrl}}").into(holder.avatar);

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_starred_repo, parent, false)
        )
    }

    object DataDifferntiator : DiffUtil.ItemCallback<Repo>() {

        override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
            return oldItem == newItem
        }
    }

}
