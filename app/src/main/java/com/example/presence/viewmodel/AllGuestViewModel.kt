package com.example.presence.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AllGuestViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is all guests fragment"
    }
    val text: LiveData<String> = _text
}