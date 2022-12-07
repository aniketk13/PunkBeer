package com.example.jumpingminds

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jumpingminds.api.RetrofitInstance
import com.example.jumpingminds.api.models.Beer
import kotlinx.coroutines.launch

class BeerDetailsViewModel : ViewModel() {

    private val _requests: MutableLiveData<ArrayList<Beer>> = MutableLiveData()
    val requests: LiveData<ArrayList<Beer>>
        get() = _requests

    fun getBeerById(id: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getBeerById(id)
                _requests.value = response
                Log.i("helloabc", "inside view model")
            } catch (e: Exception) {
                Log.i("Error", e.toString())
            }
        }
    }
}