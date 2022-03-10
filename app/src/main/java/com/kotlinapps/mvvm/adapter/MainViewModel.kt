package com.kotlinapps.mvvm.adapter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlinapps.mvvm.model.Blog

class MainViewModel : ViewModel() {
    var mutableLiveData = MutableLiveData<ArrayList<Blog>>()
    var newlist = arrayListOf<Blog>()

    fun add(blog: Blog) {
        newlist.add(blog)
        mutableLiveData.value = newlist
    }

    fun remove(blog: Blog) {
        newlist.remove(blog)
        mutableLiveData.value = newlist
    }

    fun clear() {
        newlist.clear()
        mutableLiveData.value = newlist
    }
}