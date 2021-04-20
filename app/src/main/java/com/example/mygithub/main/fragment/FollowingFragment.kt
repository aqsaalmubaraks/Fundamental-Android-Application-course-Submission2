package com.example.mygithub.main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithub.R
import com.example.mygithub.main.UserListAdapter
import com.example.mygithub.databinding.FragmentFollowingBinding
import com.example.mygithub.main.DetailUser
import com.example.mygithub.main.ViewModel.FollowingVM

class FollowingFragment : Fragment(R.layout.fragment_following) {
    private var binding_: FragmentFollowingBinding? = null
    private val binding get() = binding_!!
    private lateinit var ViewModelFollowing: FollowingVM
    private lateinit var userListAdapter: UserListAdapter
    private lateinit var githubUsername: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        super.onViewCreated(view, savedInstanceState)
        githubUsername = arguments?.getString(DetailUser.EXTRA_USERNAME).toString()

        binding_ = FragmentFollowingBinding.bind(view)

        userListAdapter = UserListAdapter()
        userListAdapter.notifyDataSetChanged()

        binding.rvfollowing.setHasFixedSize(true)
        binding.rvfollowing.layoutManager = LinearLayoutManager(activity)
        binding.rvfollowing.adapter = userListAdapter

        showBuffer(true)

        ViewModelFollowing = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(FollowingVM::class.java)
        ViewModelFollowing.setListFollowing(githubUsername)
        ViewModelFollowing.getListFollowing().observe(viewLifecycleOwner, {
            if (it != null) {
                userListAdapter.setterList(it)
                showBuffer(false)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding_ = FragmentFollowingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun showBuffer(state: Boolean) {
        if (state){
            binding.barProgress.visibility = View.VISIBLE
        } else {
            binding.barProgress.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding_ = null
    }
}