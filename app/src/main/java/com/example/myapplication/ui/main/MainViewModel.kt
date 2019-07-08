package com.example.myapplication.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.delay

class MainViewModel : ViewModel() {

    private var age = 29
    private var money = 1000
    private var delay = 2_000L

    private val data: LiveData<Data> = liveData {
        while (true) {
            delay(delay)

            emit(Data("Mohit", age, money))

            money++
            age++
            delay += 200
        }
    }

    fun getData() = data
}

data class Data(val name: String, val age: Int, val money: Int)
