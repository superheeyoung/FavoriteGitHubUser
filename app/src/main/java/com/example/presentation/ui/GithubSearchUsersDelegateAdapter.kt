package com.example.presentation.ui


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.favoritesgithubapi.databinding.ItemGithubUserListBinding
import com.example.presentation.base.BaseViewHolder
import com.example.presentation.model.GithubUserModel
import com.example.presentation.model.ViewTypeModel
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate

class GithubSearchUsersDelegateAdapter(val context: Context) :
    AdapterDelegate<List<ViewTypeModel>>() {

    var onItemClick: ((GithubUserModel) -> Unit)? = null

    override fun isForViewType(items: List<ViewTypeModel>, position: Int): Boolean {
        return items[position] is GithubUserModel
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val bindingView =
            ItemGithubUserListBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(bindingView)
    }

    override fun onBindViewHolder(
        items: List<ViewTypeModel>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val item = items[position] as GithubUserModel

        with(holder as ViewHolder) {
            viewBinding.tvUserName.text = item.name
            Glide.with(context).load(item.avatarUrl).into(viewBinding.imgUserAvatar)
            if (item.isCheck) viewBinding.checkUserFavorites.isChecked = true

            viewBinding.checkUserFavorites.setOnClickListener {
                onItemClick?.invoke(item)
            }

        }
    }

    class ViewHolder(var viewBinding: ItemGithubUserListBinding) : BaseViewHolder(viewBinding.root)


}