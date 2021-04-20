package com.example.mygithub.main.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mygithub.main.retro.API
import com.example.mygithub.main.retro.PeopleDetail
import retrofit2.Call
import retrofit2.Response

class ModelDetail: ViewModel(){
    val userDetail = MutableLiveData<PeopleDetail>()

    fun setGithubDetail(username: String) {
        API.apiReq
            .getUserDetail(username)
            .enqueue(object : retrofit2.Callback<PeopleDetail>{
                override fun onResponse(call: Call<PeopleDetail>, response: Response<PeopleDetail>
                ) {
                    if (response.isSuccessful){
                        userDetail.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<PeopleDetail>, error: Throwable) {
                    Log.d("Failure", error.message.toString())
                }
            })
    }
    fun getGithubDetail(): LiveData<PeopleDetail> = userDetail
}