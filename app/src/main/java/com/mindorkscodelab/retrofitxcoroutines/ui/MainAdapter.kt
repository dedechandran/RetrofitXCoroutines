package com.mindorkscodelab.retrofitxcoroutines.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mindorkscodelab.retrofitxcoroutines.R
import com.mindorkscodelab.retrofitxcoroutines.data.model.User
import kotlinx.android.synthetic.main.user_item.view.*

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(user: User) {
            itemView.apply {
                textViewUserName.text = user.userName
                textViewUserEmail.text = user.userEmail
                Glide.with(itemView)
                    .load(user.userImage)
                    .into(imageViewAvatar)
            }
        }
    }

    private val users = mutableListOf<User>()

    fun setData(users: List<User>) {
        this.users.apply {
            clear()
            addAll(users)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        )
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: MainAdapter.MainViewHolder, position: Int) {
        holder.bindItem(users[position])
    }
}