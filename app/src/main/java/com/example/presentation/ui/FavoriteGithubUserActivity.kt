package com.example.presentation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.favoritesgithubapi.databinding.ActivityFavoriteGithubUserBinding
import com.example.presentation.extension.Result
import com.example.presentation.viewModel.GithubUserMainViewModel
import org.koin.android.ext.android.inject
/*
* 유저가 즐겨찾기한 깃헙 유저의 목록을 보는 클래스입니다.
* */
class FavoriteGithubUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteGithubUserBinding

    private val githubUserMainViewModel: GithubUserMainViewModel by inject()

    private val githubSearchUsersAdapter: GithubSearchUsersAdapter by lazy {
        GithubSearchUsersAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoriteGithubUserBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        with(binding.rvGithubUsers) {
            isNestedScrollingEnabled = false
            setHasFixedSize(true)
            adapter = githubSearchUsersAdapter
            layoutManager = LinearLayoutManager(this@FavoriteGithubUserActivity)
        }

        githubUserMainViewModel.favoriteUser()

        githubUserMainViewModel.favoriteUserEvent.observe(this) {
            when (it) {
                is Result.Loading -> {
                    binding.loadingProgress.isVisible = true
                }
                is Result.Success -> {
                    binding.loadingProgress.isVisible = false
                    githubSearchUsersAdapter.items = it.data
                    githubSearchUsersAdapter.notifyDataSetChanged()
                }
                is Result.Error -> {
                    Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                }
                else -> binding.loadingProgress.isVisible = false
            }
        }

        githubSearchUsersAdapter.setOnItemClickListener {
            if (it.isCheck) githubUserMainViewModel.deleteItem(it.name, true)
            else githubUserMainViewModel.insertFavoriteUser(it.copy(isCheck = true))
        }
    }
}