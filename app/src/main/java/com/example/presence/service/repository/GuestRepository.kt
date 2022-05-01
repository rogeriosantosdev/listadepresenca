package com.example.presence.service.repository

import android.content.ContentValues
import android.content.Context
import androidx.core.content.contentValuesOf
import com.example.presence.service.constants.DataBaseConstants
import com.example.presence.service.model.GuestModel
import java.lang.Exception

class GuestRepository (context: Context) {

    private val mDataBase = GuestDataBase.getDatabase(context).guestDAO()


    fun get(id: Int): GuestModel {
        return mDataBase.get(id)
    }

    fun save(guest: GuestModel): Boolean {
        return mDataBase.save(guest) > 0
    }

    fun getAll(): List<GuestModel> {
        return mDataBase.getInvited()
    }

    fun getPresent(): List<GuestModel> {
        return mDataBase.getPresent()
    }

    fun getAbsent(): List<GuestModel> {
        return mDataBase.getAbsent()
    }


    fun update(guest: GuestModel): Boolean {
        return mDataBase.update(guest) > 0
    }

    fun delete(guest: GuestModel) {
        mDataBase.delete(guest)
    }

}