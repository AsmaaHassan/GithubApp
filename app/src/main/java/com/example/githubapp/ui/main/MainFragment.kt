package com.example.githubapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.GithubApp
import com.example.githubapp.R
import com.example.githubapp.viewmodels.ReposViewModel
import com.example.githubapp.viewmodels.ReposViewModelFactory
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import org.kodein.di.DIAware
import org.kodein.di.instance
/**
 * Created by Asmaa Hassan
 */
class MainFragment : Fragment(), DIAware {
    //Dependency injection
    override val di by lazy { (requireActivity().applicationContext as GithubApp).di }
    private val repoViewModelFactory: ReposViewModelFactory by instance()
    private val repoViewModel: ReposViewModel by lazy {
        ViewModelProvider(
            this,
            repoViewModelFactory
        ).get(ReposViewModel::class.java)
    }

    //data
    private lateinit var starredReposAdapter: StarredReposAdapter

    //views
    lateinit var rootView: View

    companion object {
        fun newInstance() = MainFragment()
    }
    private fun setupView() {
        lifecycleScope.launch {
            repoViewModel.starredRepos.collect {
                starredReposAdapter.submitData(it)
            }
        }
    }

    private fun setupList() {
        starredReposAdapter = StarredReposAdapter(requireContext())

        rootView.findViewById<RecyclerView>(R.id.rvStarredRepos).apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = starredReposAdapter
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootView = inflater.inflate(R.layout.main_fragment, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
        setupView()
    }

}