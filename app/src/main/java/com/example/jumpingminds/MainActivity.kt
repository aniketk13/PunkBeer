package com.example.jumpingminds

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.jumpingminds.api.RetrofitInstance
import com.example.jumpingminds.api.models.Beer
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var response:ArrayList<Beer>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        lifecycleScope.launch {
//            response=RetrofitInstance.api.getAllBeer()
//            Log.i("helloabc",response.toString())
//        }
    }
}