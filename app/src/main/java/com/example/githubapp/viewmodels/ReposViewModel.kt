 package com.example.githubapp.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.githubapp.GithubApp
import com.example.githubapp.data.remote.RemoteDataSource
import com.example.githubapp.data.remote.RetrofitProvider
import com.example.githubapp.models.Repo
import kotlinx.coroutines.flow.Flow
import org.kodein.di.*
 /**
  * Created by Asmaa Hassan
  */
class ReposViewModel(ctx: Context) : ViewModel(), DIAware {
    override val di by lazy { (ctx as GithubApp).di }

    val starredRepos: Flow<PagingData<Repo>> = Pager(PagingConfig(pageSize = 6)) {
        RemoteDataSource(RetrofitProvider.getInstance())
    }.flow.cachedIn(viewModelScope)
}


