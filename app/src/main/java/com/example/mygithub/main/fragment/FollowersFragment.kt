package com.example.mygithub.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithub.databinding.FollowersFragmentBinding
import com.example.mygithub.main.DetailUser
import com.example.mygithub.main.UserListAdapter
import com.example.mygithub.main.ViewModel.FollowersVM

@Suppress("DEPRECATION")
class FollowersFragment : Fragment() {
    private var binding_: FollowersFragmentBinding? = null
    private val binding get() = binding_!!
    private lateinit var userListAdapter: UserListAdapter
    private lateinit var username: String
    private lateinit var ViewModelFollowers: FollowersVM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        username = arguments?.getString(DetailUser.EXTRA_USERNAME).toString()
        binding_ = FollowersFragmentBinding.bind(view)

        userListAdapter = UserListAdapter()
        userListAdapter.notifyDataSetChanged()

        binding.rvfollowers.setHasFixedSize(true)
        binding.rvfollowers.layoutManager = LinearLayoutManager(activity)
        binding.rvfollowers.adapter = userListAdapter

        showData(true)

        ViewModelFollowers = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(FollowersVM::class.java)

        ViewModelFollowers.setlistOfFollowers(username)
        ViewModelFollowers.getListFollowers().observe(viewLifecycleOwner, {
            if (it != null) {
                userListAdapter.setterList(it)
                showData(false)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding_ = FollowersFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun showData(state: Boolean) {
        if (state) {
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
