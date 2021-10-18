package com.example.githubapp

import android.app.Application
import com.example.githubapp.data.remote.RemoteDataSource
import com.example.githubapp.data.remote.RetrofitProvider
import com.example.githubapp.viewmodels.ReposViewModelFactory
import org.kodein.di.*
import retrofit2.Retrofit
/**
 * Created by Asmaa Hassan
 */
class GithubApp : Application() {
    override fun onCreate() {
        super.onCreate()

    }

    val di = DI {
        bind<Retrofit>() with singleton { RetrofitProvider.getInstance() }
        bind<RemoteDataSource>() with  singleton { RemoteDataSource(instance()) }
        bind<ReposViewModelFactory>() with provider { ReposViewModelFactory(this@GithubApp) }
    }
}