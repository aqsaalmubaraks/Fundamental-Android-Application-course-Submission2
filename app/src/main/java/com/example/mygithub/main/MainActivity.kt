package com.example.mygithub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithub.databinding.ActivityMainBinding
import com.example.mygithub.main.DetailUser
import com.example.mygithub.main.DetailUser.Companion.EXTRA_USERNAME
import com.example.mygithub.main.UserListAdapter
import com.example.mygithub.main.ViewModel.ModelMain
import com.example.mygithub.main.retro.PeopleDetail
import com.example.mygithub.main.retro.PeopleModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userListGithubAdapter: UserListAdapter
    private lateinit var userViewModel: ModelMain


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val searchUser = "Cari User lain!"
        val textHome = "Ayo cari User lain di Github!"
        binding.BarSearch.hint = searchUser
        binding.TextMain.text = textHome

        userListGithubAdapter = UserListAdapter()
        userListGithubAdapter.notifyDataSetChanged()

        binding.apply {
            rvMain.setHasFixedSize(true)
            rvMain.layoutManager = LinearLayoutManager(this@MainActivity)
            rvMain.adapter = userListGithubAdapter

            BarSearch.setOnKeyListener { _, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    userSearch()
                    showBuffer(true)
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
            searchbaricon.setOnClickListener{
                userSearch()
            }
        }

        userListGithubAdapter.setOnClickBack(object : UserListAdapter.OnItemClickBack {
            override fun onItemClicked(data: PeopleModel) {
                val intent = Intent(this@MainActivity, DetailUser::class.java)
                intent.putExtra(DetailUser.EXTRA_USERNAME, data.login)
                startActivity(intent)
            }
        })
        userViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(ModelMain::class.java)
        userViewModel.getSearchUser().observe(this, {
            if (it != null) {
                userListGithubAdapter.setterList(it)
                showBuffer(false)
            }
        })

    }
    private fun showBuffer(state: Boolean) {
        if (state){
            binding.apply {
                barProgress.visibility = View.VISIBLE
                mainPhoto.visibility = View.VISIBLE
                TextMain.visibility = View.VISIBLE
                rvMain.visibility = View.INVISIBLE
            }
        } else {
            binding.apply {
                barProgress.visibility = View.INVISIBLE
                mainPhoto.visibility = View.INVISIBLE
                TextMain.visibility = View.INVISIBLE
                rvMain.visibility = View.VISIBLE
            }
        }

    }

    private fun userSearch() {
        binding.apply {
            val query = BarSearch.text.toString()

            if (query.isEmpty())
                showBuffer(false)
            userViewModel.setUserSearch(query)
        }
    }

}