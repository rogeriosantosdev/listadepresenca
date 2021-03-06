package com.example.presence.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.presence.service.constants.GuestConstants
import com.example.presence.service.model.GuestModel
import com.example.presence.service.repository.GuestRepository

class GuestViewModel(application: Application) : AndroidViewModel(application) {

    private val mGuestRepository = GuestRepository(application.applicationContext)

    private val mGuestList = MutableLiveData<List<GuestModel>>()
    val guestList: LiveData<List<GuestModel>> = mGuestList

    fun load(filter: Int){

        if(filter == GuestConstants.FILTER.EMPTY){
            mGuestList.value = mGuestRepository.getAll()
        } else if(filter == GuestConstants.FILTER.PRESENT){
            mGuestList.value = mGuestRepository.getPresent()
        } else {
            mGuestList.value = mGuestRepository.getAbsent()
        }
    }

    fun delete(id: Int){
        val guest = mGuestRepository.get(id)
        mGuestRepository.delete(guest)
    }
}