package com.example.presence.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.presence.service.model.GuestModel
import com.example.presence.service.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application){

    private val mContext = application.applicationContext
    private val mGuestRepository: GuestRepository = GuestRepository.getInstance(mContext)
    private var mSaveGuest = MutableLiveData<Boolean>()
    val saveGuest: LiveData<Boolean> = mSaveGuest


    fun save(name: String, presence: Boolean){
        val guest = GuestModel(name, presence)
        mGuestRepository.save(guest)
    }

}