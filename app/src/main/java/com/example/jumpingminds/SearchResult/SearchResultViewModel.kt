package com.example.jumpingminds.SearchResult

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jumpingminds.api.RetrofitInstance
import com.example.jumpingminds.api.models.Beer
import kotlinx.coroutines.launch

class SearchResultViewModel : ViewModel() {
    private val _requests: MutableLiveData<ArrayList<Beer>> = MutableLiveData()
    val requests: LiveData<ArrayList<Beer>>
        get() = _requests

    private val _beer: MutableLiveData<Beer> = MutableLiveData()
    val beer: LiveData<Beer>
        get() = _beer

    fun getBeer() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getAllBeer()
                _requests.value = response
                Log.i("helloabc", "inside view model")
            } catch (e: Exception) {
                Log.i("Error", e.toString())
            }
        }
    }

    fun getBeerByBeerName(beer_name: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getBeerByBeerName(beer_name)
                _requests.value = response
            } catch (e: Exception) {
                Log.i("Error", e.toString())
            }
        }
    }

    fun getBeerByYeast(yeast: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getBeerByYeast(yeast)
                _requests.value = response
            } catch (e: Exception) {
                Log.i("Error", e.toString())
            }
        }
    }

    fun getBeerByMalt(malt: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getBeerByMalt(malt)
                _requests.value = response
            } catch (e: Exception) {
                Log.i("Error", e.toString())
            }
        }
    }

    fun getBeerByFood(food: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getBeerByFood(food)
                _requests.value = response
            } catch (e: Exception) {
                Log.i("Error", e.toString())
            }
        }
    }

    fun getBeerByHops(hops: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getBeerByHops(hops)
                _requests.value = response
            } catch (e: Exception) {
                Log.i("Error", e.toString())
            }
        }
    }
}