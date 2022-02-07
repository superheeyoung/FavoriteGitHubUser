package com.example.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.favoritesgithubapi.databinding.ActivityGithubSearchUsersBinding
import com.example.presentation.extension.onSearch
import com.example.presentation.viewModel.GithubUserMainViewModel
import com.example.presentation.extension.Result
import com.example.presentation.extension.launchActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.subjects.BehaviorSubject
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit

/*
* GithubApi를 이용하여 검색한 유저의 목록을 불러오는 클래스입니다.
* */
class GithubSearchUsersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGithubSearchUsersBinding
    private val githubUserMainViewModel: GithubUserMainViewModel by inject()
    private var etSearchName = ""


    val disposable: CompositeDisposable = CompositeDisposable()


    private var subject: BehaviorSubject<String> = BehaviorSubject.create()

    private val githubSearchUsersAdapter: GithubSearchUsersAdapter by lazy {
        GithubSearchUsersAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGithubSearchUsersBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        with(binding.rvGithubUsers) {
            isNestedScrollingEnabled = false
            setHasFixedSize(true)
            adapter = githubSearchUsersAdapter
            layoutManager = LinearLayoutManager(this@GithubSearchUsersActivity)
        }

        binding.etSearch.onSearch {
            if (it.isNotBlank()) etSearchName = it
        }

        binding.etSearch.doAfterTextChanged {
            subject.onNext(binding.etSearch.text.toString())
        }

        binding.btnSearch.setOnClickListener {
            githubUserMainViewModel.searchGithubUser(etSearchName)
        }

        binding.btnFavoriteUser.setOnClickListener {
            launchActivity<FavoriteGithubUserActivity>()
        }

        githubUserMainViewModel.searchResultEvent.observe(this) {
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
                    binding.loadingProgress.isVisible = false
                    Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                }
                else -> binding.loadingProgress.isVisible = false
            }
        }

        githubSearchUsersAdapter.setOnItemClickListener {
            if (it.isCheck) githubUserMainViewModel.deleteItem(it.name)
            else githubUserMainViewModel.insertFavoriteUser(it.copy(isCheck = true))
        }

        disposable += searchSubject()
    }

    private fun searchSubject() =
        subject.debounce(500, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ search ->
                githubUserMainViewModel.searchGithubUser(search)
            }, {})

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}