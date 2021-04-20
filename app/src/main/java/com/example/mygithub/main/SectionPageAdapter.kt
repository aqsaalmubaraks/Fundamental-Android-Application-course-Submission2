package com.example.mygithub.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mygithub.R
import com.example.mygithub.main.fragment.FollowersFragment
import com.example.mygithub.main.fragment.FollowingFragment

class SectionPageAdapter (activity: AppCompatActivity, private val data: Bundle) :
    FragmentStateAdapter(activity){

    private val BundleFragment: Bundle = data

    private val tabName = intArrayOf(R.string.section1, R.string.section2)
    override fun getItemCount(): Int = 2
    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowersFragment()
            1 -> fragment = FollowingFragment()
        }
        fragment?.arguments = this.BundleFragment
        return fragment as Fragment
    }
}
