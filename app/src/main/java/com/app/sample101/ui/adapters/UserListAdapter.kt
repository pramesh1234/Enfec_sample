package com.app.sample101.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.app.sample101.data.model.UserModel
import com.app.sample101.databinding.ItemUserBinding

class UserListAdapter : ListAdapter<UserModel,UserListAdapter.UserListViewHolder>(Comparator) {
    inner class UserListViewHolder(private val binding: ItemUserBinding) : ViewHolder(binding.root) {
        fun bind(model: UserModel){
            binding.tvMail.text = model.email
            binding.tvUsername.text = model.username
            binding.tvCompanyName.text = model.company.name
            binding.tvName.text = model.name
        }
    }
    private object Comparator : DiffUtil.ItemCallback<UserModel>() {
        override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
             return oldItem.username == newItem.username
        }

        override fun areContentsTheSame(
            oldItem: UserModel,
            newItem: UserModel
        ): Boolean {
            return oldItem==newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        Log.d("TAG", "onCreateViewHolder: ")
        return UserListViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}