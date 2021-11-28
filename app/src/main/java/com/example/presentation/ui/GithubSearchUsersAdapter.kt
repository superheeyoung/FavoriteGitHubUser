package com.example.presentation.ui

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.model.GithubUserModel
import com.example.presentation.model.ViewTypeModel
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class GithubSearchUsersAdapter(context : Context) : ListDelegationAdapter<List<ViewTypeModel>>() {

    private val githubSearchUserDelegationAdapter = GithubSearchUsersDelegateAdapter(context)
    lateinit var gitHubUserRecyclerView : RecyclerView

    init {
        delegatesManager.addDelegate(githubSearchUserDelegationAdapter)
    }

    fun setOnItemClickListener(listener:(GithubUserModel)->Unit) {
        githubSearchUserDelegationAdapter.onItemClick = listener
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        gitHubUserRecyclerView = recyclerView
    }




}