package com.example.mygithub.main.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mygithub.main.retro.API
import com.example.mygithub.main.retro.PeopleModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingVM: ViewModel() {
    val listOfFollowing = MutableLiveData<ArrayList<PeopleModel>>()

    fun setListFollowing(username: String){
        API.apiReq
            .getUserFollowing(username)
            .enqueue(object : Callback<ArrayList<PeopleModel>> {
                override fun onResponse(
                    call: Call<ArrayList<PeopleModel>>,
                    response: Response<ArrayList<PeopleModel>>
                ) {
                    if (response.isSuccessful){
                        listOfFollowing.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<PeopleModel>>, error: Throwable) {
                    Log.d("Failure", error.message.toString())
                }
            })
    }
    fun getListFollowing(): LiveData<ArrayList<PeopleModel>> = listOfFollowing
}