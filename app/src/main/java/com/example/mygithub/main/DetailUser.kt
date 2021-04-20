package com.example.mygithub.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mygithub.R
import com.example.mygithub.databinding.ActivityDetailUserBinding
import com.example.mygithub.main.ViewModel.ModelDetail
import com.example.mygithub.main.retro.PeopleModel
import com.google.android.material.tabs.TabLayoutMediator

class DetailUser : AppCompatActivity() {

    companion object{
        const val EXTRA_USERNAME = "username"
    }
    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var detailGithub: ModelDetail

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val followers = resources.getString(R.string.Followers)
        val following = resources.getString(R.string.Following)
        val repository = resources.getString(R.string.Repository)
        binding.apply {
            Followers.text = followers
            Following.text = following
            Repository.text = repository
        }

        val githubUname = intent.getStringExtra(EXTRA_USERNAME)
        val saveData = Bundle()
        saveData.putString(EXTRA_USERNAME, githubUname)

        detailGithub = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(ModelDetail::class.java)
        detailGithub.setGithubDetail(githubUname.toString())
        detailGithub.getGithubDetail().observe(this,{
            if (it != null){
                binding.apply {
                    Name.text = it.name
                    txtUserName.text = it.login
                    Followers.text = it.followers.toString()
                    Following.text = it.following.toString()
                    Repository.text = it.public_repos.toString()
                    Company.text = it.company ?: "No Company"
                    Location.text = it.location ?: "No Location"
                    Glide.with(this@DetailUser)
                        .load(it.avatar_url)
                        .into(AvatarDetail)
                }
            }
        })
        val tabName = intArrayOf(R.string.section1, R.string.section2)
        val sectionPageAdapter = SectionPageAdapter(this, saveData)
        binding.ViewSection.adapter = sectionPageAdapter
        TabLayoutMediator(binding.section, binding.ViewSection){ tab, position ->
            tab.text = resources.getString(tabName[position])
        }.attach()
    }
}