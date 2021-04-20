package com.example.mygithub.main.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mygithub.main.retro.API
import com.example.mygithub.main.retro.PeopleArray
import com.example.mygithub.main.retro.PeopleModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModelMain : ViewModel(){
    val listOfUserModel = MutableLiveData<ArrayList<PeopleModel>>()

    fun setUserSearch(query: String){
        API.apiReq.getUserSearch(query).enqueue(object : Callback<PeopleArray> {
            override fun onResponse(call: Call<PeopleArray>, response: Response<PeopleArray>){
                if (response.isSuccessful){
                    listOfUserModel.postValue(response.body()?.item)
                }
            }
            override fun onFailure(call: Call<PeopleArray>, error: Throwable) {
                Log.d("Failure", error.message.toString())
            }
        })
    }
    fun getSearchUser(): LiveData<ArrayList<PeopleModel>> = listOfUserModel
}