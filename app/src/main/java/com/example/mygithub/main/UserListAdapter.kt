package com.example.mygithub.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mygithub.databinding.UserItemBinding
import com.example.mygithub.main.retro.PeopleModel

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {
    private var onItemClickBack: OnItemClickBack? = null

    fun setOnClickBack (onItemClickBack: OnItemClickBack) {
        this.onItemClickBack = onItemClickBack
    }

    private val list = ArrayList<PeopleModel>()
    fun setterList(users: ArrayList<PeopleModel>) {
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()

    }
    inner class UserViewHolder(private val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(peopleModel: PeopleModel) {
            binding.apply {
                Glide.with(itemView)
                    .load(peopleModel.avatar_url)
                    .into(imgUser)

                txtNameUser.text = peopleModel.login
            }
            itemView.setOnClickListener {
                onItemClickBack?.onItemClicked(peopleModel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = UserItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickBack {
        fun onItemClicked(data: PeopleModel)
    }
}